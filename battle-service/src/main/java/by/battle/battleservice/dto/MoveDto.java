package by.battle.battleservice.dto;

import by.battle.battleservice.entity.FieldName;
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
