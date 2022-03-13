package ru.hedw1q.AskMe.models.exception;

import org.springframework.security.acls.model.NotFoundException;

/**
 * @author hedw1q
 */
public class QuestionNotFoundException extends NotFoundException {
    public QuestionNotFoundException(String msg) {
        super(msg);
    }

    public QuestionNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
