package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.service.QuestionService;

/**
 * @author hedw1q
 */
@Controller
@RequestMapping("/")
public class DefaultController {
    @Autowired
    QuestionService questionService;

    @GetMapping("{page}")
    public String getHomePage(@PathVariable("page") int page,
                              Model model){
        Pageable pageable = PageRequest.of(page,3);
        Page <Question> questionPage=questionService.getQuestionList(pageable);
        model.addAttribute("questionPage",questionPage);
        model.addAttribute("questionList",questionPage.getContent());
//questionPage.getPageable().next().getPageNumber()
        return "base";
    }
    @GetMapping()
    public String reDirectToHomePage(){
        return "redirect:/0";
    }


}