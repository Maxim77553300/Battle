package by.battle.gameservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {
    private String id;
    private LocalDateTime createdAt;
}
