package org.hdcd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {


    @RequestMapping("/login")
    public String loginForm(String error, String logout, Model model){
        return "auth/loginForm";
    }

}
