package by.battle.statisticservice.dto;

import by.battle.statisticservice.entity.Figure;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlayerFigureDto {

    @NotNull
    private Figure figure;
    @NotBlank
    private String userName;
    private String gameId;
    private String userId;
}
