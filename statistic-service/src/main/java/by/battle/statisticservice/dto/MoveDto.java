package by.battle.statisticservice.dto;


import by.battle.statisticservice.entity.FieldName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MoveDto {

    private String id;
    @NotBlank
    private String gameId;
    private String userId;
    private FieldName fieldName;
}
