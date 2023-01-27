package by.battle.gameservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FieldPlaceDto {

    @Min(1)
    @NotNull
    private Integer horizontalIndex;
    @Min(1)
    @NotNull
    private Integer verticalIndex;
}
