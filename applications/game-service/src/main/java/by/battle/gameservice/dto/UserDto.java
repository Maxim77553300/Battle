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

    private String id;

    @Size(min = 2)
    @NotEmpty
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
