package by.battle.errorhandler.dto;

import by.battle.errorhandler.exception.ErrorCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationErrorErrorResponseDto extends BaseErrorResponseDto {

    private final List<ErrorDto> errors;

    public ValidationErrorErrorResponseDto(ErrorCategory category, String message, List<ErrorDto> errors) {
        super(category, message);
        this.errors = errors;
    }
}
