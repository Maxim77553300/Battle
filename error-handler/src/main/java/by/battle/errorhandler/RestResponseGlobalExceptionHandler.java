package by.battle.errorhandler;

import by.battle.errorhandler.dto.BaseErrorResponseDto;
import by.battle.errorhandler.dto.ErrorDto;
import by.battle.errorhandler.dto.ValidationErrorErrorResponseDto;
import by.battle.errorhandler.exception.FieldNotFreeException;
import by.battle.errorhandler.exception.InvalidAccessRightsException;
import by.battle.errorhandler.exception.ItemAlreadyExistsException;
import by.battle.errorhandler.exception.ItemNotFoundException;
import by.battle.errorhandler.exception.NotUserTurnException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> userMassageMap = createUserMassageMap(status);
        userMassageMap.putAll(ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(it -> ((FieldError) it).getField(), it -> Optional.ofNullable(it.getDefaultMessage()))));
        return new ResponseEntity<>(new ValidationErrorErrorResponseDto(status.value(), userMassageMap, getErrorsFromException(ex)), status);
    }

    @ExceptionHandler(NotUserTurnException.class)
    public ResponseEntity<?> handleNotUserTurnException(NotUserTurnException notUserTurnException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(createCustomUserMessageMap(HttpStatus.BAD_REQUEST,
                notUserTurnException.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(createCustomUserMessageMap(HttpStatus.NOT_FOUND,
                itemNotFoundException.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldNotFreeException.class)
    public ResponseEntity<?> handleFieldNotFreeException(FieldNotFreeException fieldNotFreeException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(createCustomUserMessageMap(HttpStatus.BAD_REQUEST,
                fieldNotFreeException.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccessRightsException.class)
    public ResponseEntity<?> handleInvalidAccessRightsException(InvalidAccessRightsException invalidAccessRightsException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(createCustomUserMessageMap(HttpStatus.BAD_REQUEST,
                invalidAccessRightsException.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<BaseErrorResponseDto> handleItemAlreadyExists(ItemAlreadyExistsException alreadyExistsException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(createCustomUserMessageMap(HttpStatus.BAD_REQUEST,
                alreadyExistsException.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseErrorResponseDto> handleItemAlreadyExists(RuntimeException runtimeException) {
        return new ResponseEntity<>(new BaseErrorResponseDto(createCustomUserMessageMap(HttpStatus.INTERNAL_SERVER_ERROR,
                runtimeException.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> createUserMassageMap(HttpStatus httpStatus) {
        Map<String, Object> userMassage = new LinkedHashMap<>();
        userMassage.put("timestamp", new Date());
        userMassage.put("status", httpStatus);
        return userMassage;
    }

    private Map<String, Object> createCustomUserMessageMap(HttpStatus httpStatus, String message) {
        Map<String, Object> userMessage = createUserMassageMap(httpStatus);
        userMessage.put("userMessage", message);
        return userMessage;
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
