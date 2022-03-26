package ru.hedw1q.AskMe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Answer;
import ru.hedw1q.AskMe.repository.AnswerRepository;
import ru.hedw1q.AskMe.service.AnswerService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hedw1q
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    AnswerRepository answerRepository;

    public List<? extends Answer> getAnswerList(Long id) {
        List<Answer> answerList = answerRepository.findAllbyQuestionId(id);
        return answerList;
    }

    public boolean addAnswer(Answer newAnswer) {
        answerRepository.save(newAnswer);
        return true;
    }
}
