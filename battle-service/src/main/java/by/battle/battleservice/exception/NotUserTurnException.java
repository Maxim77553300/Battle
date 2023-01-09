package by.battle.battleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotUserTurnException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "It is not turn for move user with id : %s";

    public NotUserTurnException(String message) {
        super(String.format(EXCEPTION_MESSAGE, message));
    }
}
