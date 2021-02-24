package ru.hedw1q.AskMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hedw1q.AskMe.models.Question;

/**
 * @author hedw1q
 */
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
