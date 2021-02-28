package ru.hedw1q.AskMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.repository.QuestionRepository;
import ru.hedw1q.AskMe.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author hedw1q
 * Service class for Question entity. Provides methods used in QuestionController class.
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
    public List<? extends Question> getQuestionList(){
        List <Question> questionList=new ArrayList<>();
        questionList= questionRepository.findAll();
        return questionList;
    }
    public Question getQuestion(long id){
        Question question = questionRepository.findById(id).orElse(new Question());
        return question;
    }


}
