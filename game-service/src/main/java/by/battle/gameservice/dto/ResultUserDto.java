package by.battle.gameservice.dto;

import by.battle.gameservice.entity.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ResultUserDto extends BaseDto {

    private Result result;
    private String userId;
    private String gameId;
}
