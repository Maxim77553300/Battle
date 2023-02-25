package by.battle.statisticservice.dto;

import by.battle.statisticservice.entity.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GameDto {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private GameStatus status;

    @Min(3)
    @Max(10)
    private Integer size;

    @NotEmpty
    @Size(min = 2, max = 2)
    private List<UserDto> users;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<MoveDto> moves;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ResultUserDto> results;

    @NotEmpty
    @Size(min = 2, max = 2)
    private List<PlayerFigureDto> playerFigures;
}
