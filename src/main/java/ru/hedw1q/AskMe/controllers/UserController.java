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

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(value = "error", defaultValue = "false") boolean loginError) {
        if (loginError) {
            model.addAttribute("error", "Username or password is incorrect");
        }
        System.out.println(model.getAttribute("error"));
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "register";
        }
        try{
            userService.saveUser(userForm);
        }catch (UserAlreadyExistException userAlreadyExistException){
            bindingResult.rejectValue("username", "userForm.username", userAlreadyExistException.getMessage());
            model.addAttribute("userForm", userForm);
            return "register";
        }
        return "redirect:/";
    }

}

