package ru.hedw1q.AskMe.models;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNullFormatVisitor;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author hedw1q
 */
public class BaseEntryTest {
    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }
    @Test
    void shouldNotValidateWhenBodyEmpty() {
        BaseEntry baseEntry = new BaseEntry();
        baseEntry.setBody("");


        Validator validator = createValidator();
        Set<ConstraintViolation<BaseEntry>> constraintViolations = validator.validate(baseEntry);

        assertThat(constraintViolations).hasSize(1);
        ConstraintViolation<BaseEntry> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("body");
        assertThat(violation.getMessage()).isEqualTo("Тело не должно быть пустым");
    }
    @Test
    void shouldNotValidateWhenTitleEmpty() {
        Question question=new Question();
        question.setTitle("");
        question.setBody("body");


        Validator validator = createValidator();
        Set<ConstraintViolation<BaseEntry>> constraintViolations = validator.validate(question);

        assertThat(constraintViolations).hasSize(1);
        ConstraintViolation<BaseEntry> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("title");
        assertThat(violation.getMessage()).isEqualTo("Заголовок вопроса не должен быть пустым");
    }
}
