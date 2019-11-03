package com.cpunisher.controller;

import com.cpunisher.model.User;
import com.cpunisher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute User user, Errors errors, ModelAndView mv) {
        if (errors.hasErrors()) {
            mv.addObject("message", errors.getAllErrors().get(0).getDefaultMessage());
            mv.setViewName("registerForm");
            return mv;
        }
        User user0 = userService.getUserByOpenId(user.getOpenId());

        if (user0 != null) {
            mv.addObject("error", "ID已经被注册!");
            mv.setViewName("registerForm");
        } else {
            userService.register(user.getOpenId(), user.getPassword(), user.getNickname(), null);
            mv.setViewName("redirect:loginForm");
        }
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session) {
        session.removeAttribute("user");
        mv.setViewName("redirect:loginForm");
        return mv;
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public ModelAndView login(String openId, String password, ModelAndView mv, HttpSession session) {
        User user0 = (User) session.getAttribute("user");
        if (user0 != null) {
            mv.setViewName("redirect:lobby");
            return mv;
        }

        User user = userService.validateUser(openId, password);

        if (user != null) {
            session.setAttribute("user", user);
            mv.setViewName("redirect:lobby");
        } else {
            mv.addObject("message", "ID或者密码输入错误!");
            mv.setViewName("forward:loginForm");
        }
        return mv;
    }

    @RequestMapping("/index")
    public String index(HttpSession session) {
        return "lobby";
    }
}
