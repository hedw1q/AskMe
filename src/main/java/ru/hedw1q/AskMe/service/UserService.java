package ru.hedw1q.AskMe.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.hedw1q.AskMe.models.User;

import java.util.List;


/**
 * @author hedw1q
 * Service class for User entity. Provides all methods used in UserController class.
 */
public interface UserService {
    User findUserByUserName(String username);

    User findUserById(Long userId);

    List<User> getAllUsers();

    boolean saveUser(User user);

    boolean deleteUserbyId(Long userId);

    boolean deleteUserbyUsername(String username);

    void login(String username, String password);
}
