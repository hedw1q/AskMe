package ru.hedw1q.AskMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hedw1q.AskMe.models.User;

/**
 * @author hedw1q
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
