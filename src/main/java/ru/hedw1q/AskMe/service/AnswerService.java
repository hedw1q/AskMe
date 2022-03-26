package ru.hedw1q.AskMe.service;

import ru.hedw1q.AskMe.models.Answer;
import java.util.List;

/**
 * @author hedw1q
 * Service class for Answer entity. Provides methods used in QuestionController class.
 */

public interface AnswerService {
    List<? extends Answer> getAnswerList(Long id);
    boolean addAnswer(Answer newAnswer);
}
