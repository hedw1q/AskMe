package ru.hedw1q.AskMe.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.hedw1q.AskMe.controllers.UserController;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for {@link UserController}
 *
 * @author hedw1q
 */
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    private User testUser;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        testUser = new User();
        testUser.setId(12L);
        testUser.setUsername("testUser");
        testUser.setPassword("testUserPassword");
        userService.saveUser(testUser);
    }

    @Test
    void testRegisterFormSuccess() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "Joe")
                .param("password", "12345")
        )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(view().name("register"));
    }
}
