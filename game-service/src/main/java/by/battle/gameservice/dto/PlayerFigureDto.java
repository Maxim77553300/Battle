package by.battle.gameservice.dto;

import by.battle.gameservice.entity.Figure;
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
