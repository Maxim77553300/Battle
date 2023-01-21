package by.battle.statisticservice.dto;

import ch.qos.logback.core.status.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDto {

    private String id;
    @Size(min = 2)
    @NotEmpty
    private String name;
    @Size(min = 5)
    @NotEmpty
    private String password;
    @Email
    @NotEmpty
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<RoleDto> roles;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Status status;
}
