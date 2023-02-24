package by.battle.errorhandler;

import by.battle.errorhandler.dto.BaseErrorResponseDto;
import by.battle.errorhandler.dto.ErrorDto;
import by.battle.errorhandler.dto.ValidationErrorErrorResponseDto;
import by.battle.errorhandler.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class RestResponseGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ValidationErrorErrorResponseDto(ErrorCategory.VALIDATION_ERROR,
                        "Inputs are incorrect",
                        getErrorsFromException(ex))
        , status);
    }

    @ExceptionHandler(NotUserTurnException.class)
    public ResponseEntity<?> handleNotUserTurnException(NotUserTurnException notUserTurnException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(ErrorCategory.UNPROCESSABLE_OPERATION,
                notUserTurnException.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new BaseErrorResponseDto(ErrorCategory.RESOURCE_NOT_FOUND,
                ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldNotFreeException.class)
    public ResponseEntity<?> handleFieldNotFreeException(FieldNotFreeException fieldNotFreeException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(ErrorCategory.UNPROCESSABLE_OPERATION,
                fieldNotFreeException.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidAccessRightsException.class)
    public ResponseEntity<?> handleInvalidAccessRightsException(InvalidAccessRightsException ex) {
        return new ResponseEntity<>(new BaseErrorResponseDto(ErrorCategory.ACCESS_DENIED_ERROR, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<BaseErrorResponseDto> handleNotSpecificRuntimeException(ObjectAlreadyExistsException ex) {
        return new ResponseEntity<>(new BaseErrorResponseDto(ErrorCategory.OBJECT_ALREADY_EXISTS,
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseErrorResponseDto> handleNotSpecificRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new BaseErrorResponseDto(ErrorCategory.UNKNOWN_ERROR,
                ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> createMassageMap(HttpStatus httpStatus) {
        Map<String, Object> userMassage = new LinkedHashMap<>();
        userMassage.put("timestamp", new Date());
        userMassage.put("status", httpStatus);
        return userMassage;
    }

    private List<ErrorDto> getErrorsFromException(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.add(
                        new ErrorDto(
                                error.getObjectName(),
                                error.getField(),
                                error.getDefaultMessage()
                        )
                ));

        ex.getBindingResult()
                .getGlobalErrors()
                .forEach(error -> errors.add(
                        new ErrorDto(
                                error.getObjectName(),
                                ": ",
                                error.getDefaultMessage()
                        )
                ));

        return errors;
    }
}
