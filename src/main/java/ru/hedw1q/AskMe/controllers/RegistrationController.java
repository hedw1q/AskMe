package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.models.exception.UserAlreadyExistException;
import ru.hedw1q.AskMe.service.UserService;

import javax.validation.Valid;

/**
 * @author hedw1q
 */
@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

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
            userService.login(userForm.getUsername(),userForm.getPassword());
        }catch (UserAlreadyExistException userAlreadyExistException){
            bindingResult.rejectValue("username", "userForm.username", userAlreadyExistException.getMessage());
            model.addAttribute("userForm", userForm);
            return "register";
        }
        return "redirect:/";
    }
}
