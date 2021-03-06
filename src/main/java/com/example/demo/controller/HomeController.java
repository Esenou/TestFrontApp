package com.example.demo.controller;

import com.example.demo.model.UserCreat;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "layout";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "loginPage";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("user") UserCreat user) {
        userService.signIn(user);
        return "redirect:/";
    }
}
