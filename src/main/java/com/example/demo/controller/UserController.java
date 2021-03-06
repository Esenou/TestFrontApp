package com.example.demo.controller;

import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserPage;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAll(Model model, @RequestParam(value = "searchResult", required = false) String username, @RequestParam(value = "page", required = false) String page) {
        UserPage userPage = username != null ? userService.findAllByUsername(username, page) : userService.findAll(page);
        model.addAttribute("userList", userPage);
        return "userList";
    }

    @GetMapping("/add")
    public String index(Model model) {
        model.addAttribute("user", new UserModel());
        model.addAttribute("roles", Role.values());
        return "newUser";
    }

    @PostMapping("/new") // Map ONLY POST Requests
    public String addNewUser(@ModelAttribute("user") UserModel userModel, Model model) {
        userService.addUser(userModel);
        return "redirect:/user/all";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        model.addAttribute("status", UserStatus.values());
        return "user";
    }

    @PostMapping("/update/{id}") // Map ONLY POST Requests
    public String updateUser(@ModelAttribute("user") UserModel userModel, @PathVariable("id") Long id, Model model) {
        userService.updateUser(id, userModel);
        return "redirect:/user/all";
    }


}
