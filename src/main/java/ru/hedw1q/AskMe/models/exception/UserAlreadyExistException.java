package ru.hedw1q.AskMe.models.exception;

import org.springframework.security.acls.model.AlreadyExistsException;

/**
 * @author hedw1q
 */
public class UserAlreadyExistException extends AlreadyExistsException {
    public UserAlreadyExistException(String msg) {
        super(msg);
    }

    public UserAlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
