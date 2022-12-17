package by.battle.battleservice.dto;

import by.battle.battleservice.entity.Field;
import lombok.Data;

@Data
public class MoveDto {

    private Long id;
    private String gameId;
    private UserDto user;
    private Field field;
}
