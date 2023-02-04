package by.battle.errorhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidAccessRightsException extends RuntimeException {

    public InvalidAccessRightsException(String message) {
        super(message);
    }
}
