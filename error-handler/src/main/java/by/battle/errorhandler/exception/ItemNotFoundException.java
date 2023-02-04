package by.battle.errorhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemNotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Can't find item : %s";

    public ItemNotFoundException(String message) {
        super(String.format(EXCEPTION_MESSAGE, message));
    }
}
