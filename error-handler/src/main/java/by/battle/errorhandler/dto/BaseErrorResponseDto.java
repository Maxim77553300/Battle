package by.battle.errorhandler.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class BaseErrorResponseDto {
    private final Map<String, Object> userMessage;
}
