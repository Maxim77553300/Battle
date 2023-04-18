package by.battle.security.errorhandler;

import by.battle.errorhandler.dto.BaseErrorResponseDto;
import by.battle.errorhandler.exception.ErrorCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseErrorResponseDto> handleAuthenticationException(AuthenticationException e) {
        BaseErrorResponseDto errorDto = new BaseErrorResponseDto(
                ErrorCategory.AUTHENTICATION_ERROR,
                e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseErrorResponseDto> handleAccessDeniedException(AccessDeniedException e) {
        BaseErrorResponseDto errorDto = new BaseErrorResponseDto(
                ErrorCategory.ACCESS_DENIED_ERROR,
                e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.FORBIDDEN);
    }
}
