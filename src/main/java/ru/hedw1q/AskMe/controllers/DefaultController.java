package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hedw1q.AskMe.service.QuestionService;

/**
 * @author hedw1q
 */
@Controller
@RequestMapping("/")
public class DefaultController {
    @Autowired
    QuestionService questionService;

    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("questionList",questionService.getQuestionList());
        return "base";
    }


}