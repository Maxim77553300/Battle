package by.battle.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Can't find user with id : %s";

    public UserNotFoundException(String userId) {
        super(String.format(EXCEPTION_MESSAGE, userId));
    }
}
