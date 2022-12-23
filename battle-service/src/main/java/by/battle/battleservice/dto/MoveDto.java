package by.battle.battleservice.dto;

import by.battle.battleservice.entity.FieldName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MoveDto {

    private Long id;
    @NotBlank
    private String gameId;
    private FieldName fieldName;
}
