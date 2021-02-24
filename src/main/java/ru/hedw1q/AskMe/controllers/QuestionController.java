package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.service.QuestionService;

/**
 * @author hedw1q
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/ask")
    public String getAskPage(Model model){
        model.addAttribute("newQuestion", new Question());
        return "ask";
    }

    @PostMapping("/ask")
    public String askQuestion(@ModelAttribute("newQuestion") Question newQuestion, Model model)
    {

        questionService.addQuestion(newQuestion);
        return "redirect:/";
    }

}

