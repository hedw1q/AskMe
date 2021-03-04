package ru.hedw1q.AskMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Answer;
import ru.hedw1q.AskMe.repository.AnswerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hedw1q
 * Service class for Answer entity. Provides methods used in QuestionController class.
 */
@Service
public class AnswerService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    AnswerRepository answerRepository;

    public List<? extends Answer> getAnswerList(Long id) {
        List<Answer> answerList = new ArrayList<>();
        answerList = answerRepository.findAllbyQuestionId(id);
        return answerList;
    }

    public boolean addAnswer(Answer newAnswer) {
        answerRepository.save(newAnswer);
        return true;
    }


}
