package ru.hedw1q.AskMe.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import ru.hedw1q.AskMe.models.Answer;
import ru.hedw1q.AskMe.models.BaseEntry;
import ru.hedw1q.AskMe.models.Question;
import ru.hedw1q.AskMe.repository.AnswerRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for service.AnswerService class
 *
 * @author hedw1q
 */
@SpringBootTest
public class AnswerServiceTest {
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;

    @Test
    void shouldAddAnswer() {
        Answer newAnswer = new Answer();
        newAnswer.setBody("body");
        newAnswer.setQuestion(questionService.getQuestion(1L));
        newAnswer.setDate(LocalDateTime.now());

        long sizeBeforeInsert = this.answerService.getAnswerList(1L).size();

        assertThat(this.answerService.addAnswer(newAnswer)).isTrue();
        assertThat(newAnswer.getId().longValue()).isNotEqualTo(0L);
        assertThat(this.answerService.getAnswerList(1L).size()).isEqualTo(sizeBeforeInsert + 1);
    }

    @Test
    void shouldFindAllAnswers() {
        List<? extends Answer> answerList = this.answerService.getAnswerList(1L);

        assertThat(answerList.isEmpty()).isFalse();
        assertThat(answerList.get(0).getBody() == "body");
        assertThat(answerList.get(0).getId() == 8L);
    }
}
