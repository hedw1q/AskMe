package ru.hedw1q.AskMe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hedw1q.AskMe.models.Question;

/**
 * @author hedw1q
 * Repository class for Question objects.
 */
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Override
    Page<Question> findAll(Pageable pageable);

    @Query("SELECT u FROM Question u where u.body like %?1%")
    Page <Question> findByBodyContaining(String searchText, Pageable pageable);
}
