package by.battle.gameservice.dto;

import by.battle.gameservice.entity.FieldName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString(callSuper = true)
public class MoveDto extends BaseDto {

    @NotBlank
    private String gameId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;
    private FieldName fieldName;
}
