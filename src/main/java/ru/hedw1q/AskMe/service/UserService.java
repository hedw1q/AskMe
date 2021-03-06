package ru.hedw1q.AskMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Role;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.repository.RoleRepository;
import ru.hedw1q.AskMe.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 * @author hedw1q
 * Service class for User entity. Provides all methods used in UserController class.
 */
@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userRepository.findByUsername(username).get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserByUserName(String username) throws UsernameNotFoundException {
        Optional<User> userFromDb = userRepository.findByUsername(username);
        return userFromDb.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public User findUserById(Long userId) throws UsernameNotFoundException {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent() == false) {

            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUserbyId(Long userId) {
        if (userRepository.findById(userId).isPresent()) {

            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean deleteUserbyUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            long deletedId = userRepository.findByUsername(username).get().getId();
            userRepository.deleteById(deletedId);
            return true;
        }
        return false;
    }

}
