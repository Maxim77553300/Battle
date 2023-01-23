package by.battle.gameservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
public class UserDto extends BaseDto {

    private LocalDateTime updatedAt;

    @Size(min = 2)
    @NotEmpty
    private String name;
}
