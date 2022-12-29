package by.battle.battleservice.dto;

import by.battle.battleservice.entity.Result;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResultUserDto {

    private Result result;
    private String userId;
    private String gameId;
    private LocalDateTime created_at;
}
