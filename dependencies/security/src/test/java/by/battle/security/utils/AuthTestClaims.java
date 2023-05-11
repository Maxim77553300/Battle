package by.battle.security.utils;


import by.battle.common.RoleName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthTestClaims {

    private String user_name;
    private String userId;
    private List<String> authorities;

    public AuthTestClaims(List<RoleName> roleNames) {
        this.user_name = "userName";
        this.userId = "userId";
        this.authorities = createRoleNamesToAuthorities(roleNames);
    }

    private List<String> createRoleNamesToAuthorities(List<RoleName> roleNames) {
        return roleNames.stream().map(Enum::name).collect(Collectors.toList());
    }
}
