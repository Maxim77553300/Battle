package by.battle.gameservice.dto;

import by.battle.gameservice.entity.Figure;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlayerFigureDto {

    @NotNull
    private Figure figure;
    @NotBlank
    private String userName;
    private String gameId;
    private String userId;
}