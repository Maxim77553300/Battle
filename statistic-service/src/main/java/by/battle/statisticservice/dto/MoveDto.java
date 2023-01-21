package by.battle.statisticservice.dto;


import by.battle.statisticservice.entity.FieldName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MoveDto {

    private String id;
    @NotBlank
    private String gameId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;
    private FieldName fieldName;
}
