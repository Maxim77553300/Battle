package by.battle.errorhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjectAlreadyExistsException extends RuntimeException {

    private final static String MESSAGE = " is already exists";

    public ObjectAlreadyExistsException(String message) {
        super(message + MESSAGE);
    }
}
