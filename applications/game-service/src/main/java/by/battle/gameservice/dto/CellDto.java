package by.battle.gameservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CellDto {

    @Min(1)
    @NotNull
    private Integer horizontalIndex;
    @Min(1)
    @NotNull
    private Integer verticalIndex;
}
