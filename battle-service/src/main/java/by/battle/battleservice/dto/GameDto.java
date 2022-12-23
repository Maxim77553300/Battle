package by.battle.battleservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GameDto {

    private String id;
    @NotEmpty
    @Size(min = 2, max = 2)
    private List<UserDto> users;
}
