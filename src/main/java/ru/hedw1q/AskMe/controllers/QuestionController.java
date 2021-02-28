package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hedw1q.AskMe.models.Answer;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.service.AnswerService;
import ru.hedw1q.AskMe.service.QuestionService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author hedw1q
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    @GetMapping("/ask")
    public String getAskPage(Model model){
        model.addAttribute("newQuestion", new Question());
        return "ask";
    }

    @PostMapping("/ask")
    public String askQuestion(@ModelAttribute("newQuestion") Question newQuestion, Model model)
    {
        newQuestion.setRating(0);
        newQuestion.setDate(LocalDateTime.now());
        questionService.addQuestion(newQuestion);
        return "redirect:/";
    }

    @GetMapping("question/{id}")
    public String showOneQuestion(@PathVariable long id, Model model) {
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("answerList",answerService.getAnswerList(id));
        model.addAttribute("newAnswer", new Answer());
        return "question";
    }

    @PostMapping("question/{id}")
    public String addAnswer(@PathVariable long id ,@ModelAttribute("newAnswer") Answer newAnswer, Model model) {
     //   newAnswer.setDate(LocalDateTime.now());
        newAnswer.setRating(0);
        newAnswer.setQuestion(questionService.getQuestion(id));
        answerService.addAnswer(newAnswer);
        return "redirect:/question/{id}";
    }

}

