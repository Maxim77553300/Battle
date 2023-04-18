package by.battle.security.converter;

import by.battle.security.user.AuthUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AuthUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    private final static String USER_ID_KEY = "userId";

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        Authentication authentication = super.extractAuthentication(map);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        AuthUser authUser = buildUserFromMap(map, authorities);
        return new UsernamePasswordAuthenticationToken(authUser, "N/A", authorities);

    }

    private AuthUser buildUserFromMap(Map<String, ?> rawMap, Collection<? extends GrantedAuthority> authorities) {
        Map<String, String> map = rawMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, it -> it.getValue().toString()));
        return AuthUser.builder(map.get(USERNAME), "N/A", authorities)
                .userId(map.get(USER_ID_KEY))
                .build();
    }
}
