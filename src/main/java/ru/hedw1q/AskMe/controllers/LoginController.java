package ru.hedw1q.AskMe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hedw1q
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }
}
