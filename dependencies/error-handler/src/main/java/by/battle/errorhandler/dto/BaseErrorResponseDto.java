package by.battle.errorhandler.dto;

import by.battle.errorhandler.exception.ErrorCategory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BaseErrorResponseDto {

    private final ErrorCategory category;
    private final String message;
}
