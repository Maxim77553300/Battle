package by.battle.gameservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private String id;
    @Size(min = 2)
    @NotEmpty
    private String name;
}
