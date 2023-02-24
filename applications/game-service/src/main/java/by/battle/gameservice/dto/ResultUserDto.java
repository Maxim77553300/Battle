package by.battle.gameservice.dto;

import by.battle.gameservice.entity.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ResultUserDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Result result;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String gameId;
}
