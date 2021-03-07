package ru.hedw1q.AskMe.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import ru.hedw1q.AskMe.models.Question;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for service.QuestionService class
 *
 * @author hedw1q
 */
@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @Test
    void shouldAddQuestion() {
        Question newQuestion = new Question();
        newQuestion.setBody("body");
        newQuestion.setTitle("title");
        newQuestion.setAuthor("test1");
        newQuestion.setTag("TestTag");
        newQuestion.setDate(LocalDateTime.now());

        long sizeBeforeInsert = this.questionService.getQuestionList(Pageable.unpaged()).getTotalElements();

        assertThat(this.questionService.addQuestion(newQuestion)).isTrue();
        assertThat(newQuestion.getId().longValue()).isNotEqualTo(0L);
        assertThat(this.questionService.getQuestionList(Pageable.unpaged()).getTotalElements()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void shouldFindAllQuestions() {
        List<? extends Question> questionList = this.questionService.getQuestionList(Pageable.unpaged()).toList();

        assertThat(questionList.isEmpty()).isFalse();
        assertThat(questionList.get(6).getTitle() == "test");
        assertThat(questionList.get(6).getId() == 1L);
    }

    @Test
    void shouldFindQuestionById() {
        Question question = this.questionService.getQuestion(1L);
        assertThat(question.getId() == 1L).isTrue();

        Exception exception = assertThrows(NotFoundException.class, () -> {
            Question emptyQuestion = questionService.getQuestion(-3L);
        });

        String expectedMessage = "Question not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
