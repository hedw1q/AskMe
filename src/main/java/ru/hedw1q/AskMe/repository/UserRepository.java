package ru.hedw1q.AskMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hedw1q.AskMe.models.User;

/**
 * @author hedw1q
 * Repository class for User entity.This interface can be extended for the User's descendants
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieve an User from the data store by username.
     * @param username: the username to search for
     * @return the User if found
     */
    User findByUsername(String username);
}
