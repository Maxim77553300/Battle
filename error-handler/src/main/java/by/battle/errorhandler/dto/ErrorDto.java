package by.battle.errorhandler.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorDto {
    private final String objectName;
    private final String field;
    private final String defaultMessage;
}
