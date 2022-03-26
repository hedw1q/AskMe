package ru.hedw1q.AskMe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.hedw1q.AskMe.models.Question;

/**
 * @author hedw1q
 * Service class for Question entity. Provides methods used in QuestionController class.
 */
public interface QuestionService {
    boolean addQuestion(Question newQuestion);

    Page<Question> getQuestionList(Pageable pageable);

    Question getQuestion(long id);

    Page<Question> getSearchedQuestionList(String searchText, Pageable pageable);
}
