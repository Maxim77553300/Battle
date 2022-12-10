package by.battle.userservice.dto;

import by.battle.userservice.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<RoleDto> roleSet;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status;
}
