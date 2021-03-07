package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.service.UserService;


/**
 * @author hedw1q
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/settings")
    public String changeSettings() {
        return "settings";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {
        userService.saveUser(userForm);
        return "redirect:/";
    }

}

