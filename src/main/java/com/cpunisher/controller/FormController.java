package com.cpunisher.controller;

import com.cpunisher.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class FormController {

    @RequestMapping("/index")
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/loginForm";
        } else {
            return "redirect:/lobby";
        }
    }

    @RequestMapping("/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }
}
