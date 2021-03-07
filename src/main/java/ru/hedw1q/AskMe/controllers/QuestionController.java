package ru.hedw1q.AskMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hedw1q.AskMe.models.Answer;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.service.AnswerService;
import ru.hedw1q.AskMe.service.QuestionService;
import ru.hedw1q.AskMe.service.UserService;

import java.time.LocalDateTime;

/**
 * @author hedw1q
 */
@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;

    @GetMapping("/ask")
    public String getAskPage(Model model) {
        model.addAttribute("newQuestion", new Question());
        return "ask";
    }

    @PostMapping("/ask")
    public String askQuestion(@ModelAttribute("newQuestion") Question newQuestion, Model model) {
        newQuestion.setRating(0);
        newQuestion.setDate(LocalDateTime.now());
        newQuestion.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        questionService.addQuestion(newQuestion);
        return "redirect:/";
    }

    @GetMapping("question/{id}")
    public String showOneQuestion(@PathVariable long id, Model model) {
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("answerList", answerService.getAnswerList(id));
        model.addAttribute("newAnswer", new Answer());
        return "question";
    }

    @PostMapping("question/{id}")
    public String addAnswer(@PathVariable long id, @ModelAttribute("newAnswer") Answer newAnswer, Model model) {
        newAnswer.setDate(LocalDateTime.now());
        newAnswer.setRating(0);
        newAnswer.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        newAnswer.setQuestion(questionService.getQuestion(id));
        answerService.addAnswer(newAnswer);
        return "redirect:/question/{id}";
    }

    @GetMapping("/search")
    public String searchQuestions(@RequestParam(value = "searchText") String searchText,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model) {
        Pageable pageable = PageRequest.of(page, 3);
        Page<Question> questionPage = questionService.getSearchedQuestionList(searchText.toLowerCase(), pageable);
        model.addAttribute("questionPage", questionPage);
        model.addAttribute("questionList", questionPage.getContent());
        return "base";
    }


}


