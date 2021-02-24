package ru.hedw1q.AskMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.repository.QuestionRepository;
import ru.hedw1q.AskMe.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author hedw1q
 */
@Service
public class QuestionService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    QuestionRepository questionRepository;

    public boolean addQuestion(Question newQuestion) {
        questionRepository.save(newQuestion);
        return true;
    }
}
