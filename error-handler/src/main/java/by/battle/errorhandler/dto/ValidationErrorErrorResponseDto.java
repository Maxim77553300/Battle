package by.battle.errorhandler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ValidationErrorErrorResponseDto extends BaseErrorResponseDto {

    private final List<ErrorDto> errors;
    private final int code;

    public ValidationErrorErrorResponseDto(int code, Map<String, Object> userMessage, List<ErrorDto> errors) {
        super(userMessage);
        this.errors = errors;
        this.code = code;
    }
}
