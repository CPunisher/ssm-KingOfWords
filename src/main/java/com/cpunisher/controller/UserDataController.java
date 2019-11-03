package com.cpunisher.controller;

import com.cpunisher.model.User;
import com.cpunisher.model.Word;
import com.cpunisher.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/mistakes")
    public ModelAndView mistakes(ModelAndView mv ,HttpSession session) {
        mv.addObject("mistakes", loadMistakes(session));
        mv.setViewName("mistakes");
        return mv;
    }

    @PostMapping("/removeMistake")
    public ModelAndView removeMistake(@RequestParam("wordId") int wordId, ModelAndView mv, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userDataService.removeMistake(user.getId(), wordId);
        }
        mv.setViewName("mistakes");
        return mv;
    }

    @RequestMapping("/loadMistakes")
    @ResponseBody
    public Word[] loadMistakes(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Word[] words = userDataService.listMistakes(user.getId());
            return words;
        }
        return null;
    }

    @PostMapping("/saveMistakes")
    @ResponseBody
    public void saveMistakes(@RequestParam("mistakes") int[] mistakes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            for (int i = 0; i < mistakes.length; i++) {
                if (!userDataService.exists(user.getId(), mistakes[i]))
                    userDataService.addMistake(user.getId(), mistakes[i]);
            }
        }
    }
}
