package ru.hedw1q.AskMe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.models.exception.QuestionNotFoundException;
import ru.hedw1q.AskMe.repository.QuestionRepository;
import ru.hedw1q.AskMe.service.QuestionService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author hedw1q
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    QuestionRepository questionRepository;

    public boolean addQuestion(Question newQuestion) {
        questionRepository.save(newQuestion);
        return true;
    }

    public Page<Question> getQuestionList(Pageable pageable) {
        Page<Question> questionList;
        questionList = questionRepository.findAll(pageable);
        return questionList;
    }

    public Question getQuestion(long id) throws QuestionNotFoundException {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question not found"));
        return question;
    }

    public Page<Question> getSearchedQuestionList(String searchText, Pageable pageable) {
        Page<Question> questionList;
        questionList = questionRepository.findByBodyContaining(searchText, pageable);
        return questionList;
    }
}
