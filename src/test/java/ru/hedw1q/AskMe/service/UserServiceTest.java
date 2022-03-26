package ru.hedw1q.AskMe.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.hedw1q.AskMe.models.Role;
import ru.hedw1q.AskMe.models.User;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for service.UserService class
 *
 * @author hedw1q
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void shouldFindUserById() {
        User user = this.userService.findUserById(3L);
        assertThat(user.getId() == 3L).isTrue();

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            User emptyUser = userService.findUserById(-3L);
        });

        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void shouldLoadUserByUsername() {
//        UserDetails user = this.userService.loadUserByUsername("test");
//        assertThat(user.getUsername() == "test");
//
//        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
//            UserDetails emptyUser = this.userService.loadUserByUsername("");
//        });
//
//        String expectedMessage = "User not found";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

    @Test
    void shouldFindAllUsers() {
        ArrayList<User> userList = (ArrayList<User>) this.userService.getAllUsers();

        assertThat(userList.isEmpty()).isFalse();
        assertThat(userList.get(1).getUsername() == "test");
        assertThat(userList.get(1).getId() == 3L);
        assertThat(userList.get(2).getUsername() == "test1");
    }

    @Test
    void shouldInsertUser() {
        this.userService.deleteUserbyUsername("TestUser");

        User user = new User();
        user.setUsername("TestUser");
        user.setPassword(bCryptPasswordEncoder.encode("testPassword"));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        int sizeBeforeInsert = this.userService.getAllUsers().size();

        assertThat(this.userService.saveUser(user)).isTrue();
        assertThat(user.getId().longValue()).isNotEqualTo(0L);
        assertThat(this.userService.getAllUsers().size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void shouldDeleteUser() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword(bCryptPasswordEncoder.encode("testPassword"));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        this.userService.saveUser(user);

        int sizeBeforeDelete = this.userService.getAllUsers().size();

        assertThat(this.userService.deleteUserbyUsername("TestUser")).isTrue();
        assertThat(this.userService.getAllUsers().size()).isEqualTo(sizeBeforeDelete - 1);

    }

}
