package by.battle.userservice.dto;

import by.battle.userservice.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    @Size(min = 2)
    @NotEmpty
    private String name;
    @NotBlank
    @Size(min = 3, max = 50)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Email
    @NotEmpty
    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RoleDto> roles;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status;
}
