package by.battle.security.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString(callSuper = true)
public class AuthUser extends User {

    private String userId;

    @Builder
    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                    String userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public static AuthUserBuilder builder(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        return new AuthUserBuilder().username(username).password(password).authorities(authorities);
    }
}
