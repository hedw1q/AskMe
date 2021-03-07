package ru.hedw1q.AskMe.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.hedw1q.AskMe.controllers.QuestionController;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.models.User;
import ru.hedw1q.AskMe.service.AnswerService;
import ru.hedw1q.AskMe.service.QuestionService;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for {@link ru.hedw1q.AskMe.controllers.QuestionController}
 *
 * @author hedw1q
 */
@SpringBootTest
public class QuestionControllerTest {
    @Autowired
    private QuestionController questionController;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    private MockMvc mockMvc;

    private User testUser;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();

        Question testQuestion = new Question();
        testQuestion.setId(1L);
        testQuestion.setBody("body");
        testQuestion.setTitle("title");
        testQuestion.setAuthor("test1");
        testQuestion.setTag("TestTag");
        testQuestion.setDate(LocalDateTime.now());
        questionService.addQuestion(testQuestion);
    }

    @Test
    void testQuestionCreationForm() throws Exception {
        mockMvc.perform(get("/ask"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("newQuestion"))
                .andExpect(view().name("ask"));
    }

    @Test
    void testAskQuestionFormSuccess() throws Exception {
        mockMvc.perform(post("/ask")
                .param("author", "Jowie")
                .param("body", "12345")
                .param("title", "12345")
                .param("tag", "12345")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testQuestionPage() throws Exception {
        mockMvc.perform(get("/question/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attributeExists("answerList"))
                .andExpect(model().attributeExists("newAnswer"))
                .andExpect(view().name("question"));
    }

    @Test
    void testQuestionPageAddAnswer() throws Exception {
        mockMvc.perform(post("/question/{id}", 1)
                .param("body", "testbody")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/question/{id}"));
    }


}
