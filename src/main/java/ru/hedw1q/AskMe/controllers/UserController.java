package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.models.exception.UserAlreadyExistException;
import ru.hedw1q.AskMe.service.UserService;

import javax.validation.Valid;


/**
 * @author hedw1q
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/settings")
    public String changeSettings() {
        return "settings";
    }

}

