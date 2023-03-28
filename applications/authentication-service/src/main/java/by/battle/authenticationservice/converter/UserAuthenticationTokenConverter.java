package by.battle.authenticationservice.converter;

import by.battle.security.user.AuthUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserAuthenticationTokenConverter extends JwtAccessTokenConverter {

    private static final String USER_ID = "userId";
 //   private static final String ROLE_ID = "roleId";

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> response = new HashMap<>(super.convertAccessToken(token, authentication));
        if (authentication.getUserAuthentication().getPrincipal() instanceof AuthUser && !super.isRefreshToken(token)) {
            AuthUser user = (AuthUser) authentication.getUserAuthentication().getPrincipal();
            response.put(USER_ID, user.getUserId());
        }
        return response;
    }
}
