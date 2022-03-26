package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.service.QuestionService;

/**
 * @author hedw1q
 */
@Controller
@RequestMapping("/")
public class MainPageController {
    @Autowired
    QuestionService questionService;

    public enum Tab{
        NEW,
        BEST
    }

    @GetMapping()
    public String getHomePage(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "NEW") Tab tab,
                              Model model){
        Pageable pageable;
        switch(tab){
            case NEW:
                pageable = PageRequest.of(page,3, Sort.by("id").descending()); break;
            case BEST:
                pageable = PageRequest.of(page,3, Sort.by("rating").descending()); break;
            default:
                pageable = PageRequest.of(page,3);
                break;
        }
        Page <Question> questionPage=questionService.getQuestionList(pageable);
        model.addAttribute("questionPage",questionPage);
        model.addAttribute("questionList",questionPage.getContent());
        model.addAttribute("tab", tab);
        return "base";
    }

    @GetMapping("/home")
    public String redirectToHome(){
        return "redirect:/";
    }

}