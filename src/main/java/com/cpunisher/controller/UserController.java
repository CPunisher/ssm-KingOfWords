package com.cpunisher.controller;

import com.cpunisher.model.User;
import com.cpunisher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ModelAndView register(String openId, String password, String nickname, ModelAndView mv)  {
        if ("".equals(openId) || "".equals(password) || "".equals(nickname)) {
            mv.addObject("message", "ID 密码 昵称不能为空!");
            mv.setViewName("forward:/registerForm");
            return mv;
        }
        User user = userService.getUserByOpenId(openId);

        if (user != null) {
            mv.addObject("message", "ID已经被注册!");
            mv.setViewName("forward:/registerForm");
        } else {
            userService.register(openId, password, nickname, null);
            mv.setViewName("redirect:/loginForm");
        }
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session) {
        session.removeAttribute("user");
        mv.setViewName("redirect:/loginForm");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login(String openId, String password, ModelAndView mv, HttpSession session) {
        User user0 = (User) session.getAttribute("user");
        if (user0 != null) {
            mv.setViewName("redirect:/lobby");
            return mv;
        }

        User user = userService.validateUser(openId, password);

        if (user != null) {
            session.setAttribute("user", user);

            mv.setViewName("redirect:/lobby");
        } else {
            mv.addObject("message", "ID或者密码输入错误!");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    /*
    @RequestMapping("/loginByWX")
    @ResponseBody
    public Result loginByWX(String code, String nickname, String iconUrl, ModelAndView mv, HttpSession httpSession) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
            Map params = new HashMap();
            params.put("appid", APP_ID);
            params.put("secret", APP_SECRET);
            params.put("js_code", code);
            params.put("grant_type", "authorization_code");
            LoginData loginData = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}", LoginData.class, params);

            User user = userService.getUserByOpenId(loginData.getOpenid());
            if (user != null) {
                return new Result(true, StringLoader.getInstance().getString("message.loginSuccess"));
            } else {
                //TODO 默认密码修改
                userService.register(loginData.getOpenid(), "888", nickname, null);
            }
        } catch (UserRegisteredException e) {
            return new Result(false, "用户已经注册!");
        } catch (Exception e) {
            return new Result(false, "服务器内部错误!");
        }
        return new Result(true, "注册成功!");
    }

     */
}
