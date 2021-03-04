package ru.hedw1q.AskMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hedw1q.AskMe.models.Answer;

import java.util.List;

/**
 * @author hedw1q
 * Repository class for Answer objects.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT u FROM Answer u where question_id=?1")
    public List<Answer> findAllbyQuestionId(Long quest_id);


}
