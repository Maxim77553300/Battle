package by.battle.gameservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldNotFreeException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Can't set your move with name : %s because of it is not free";

    public FieldNotFreeException(String fieldName) {
        super(String.format(EXCEPTION_MESSAGE, fieldName));
    }
}
