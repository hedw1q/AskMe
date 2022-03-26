package ru.hedw1q.AskMe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hedw1q.AskMe.models.Role;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.models.exception.UserAlreadyExistException;
import ru.hedw1q.AskMe.repository.RoleRepository;
import ru.hedw1q.AskMe.repository.UserRepository;
import ru.hedw1q.AskMe.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author hedw1q
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public boolean saveUser(User user) throws UserAlreadyExistException {
        if (checkIfUserExists(user)) {
            throw new UserAlreadyExistException("User with such username already exists");
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
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

    private boolean checkIfUserExists(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public void login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
      //  authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);

            logger.info(String.format("User %s logged in successfully!", username));
        } else {
            logger.error(String.format("Error with %s authentication!", username));
        }
    }
}
