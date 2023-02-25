package by.battle.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
public class MoveDto extends BaseDto {

    @NotBlank
    private String gameId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;
    @NotNull
    private CellDto cell;
}
