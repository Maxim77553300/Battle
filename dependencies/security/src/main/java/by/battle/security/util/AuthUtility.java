package by.battle.security.util;

import by.battle.security.user.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import static java.util.Optional.ofNullable;

public class AuthUtility {

    public static String currentUserId() {
        return ((AuthUser) getCurrentAuthentication().getPrincipal()).getUserId();
    }

    private static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentToken() {
        return ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getDetails)
                .filter(it -> it instanceof OAuth2AuthenticationDetails)
                .map(it -> (OAuth2AuthenticationDetails) it)
                .map(it -> String.format("%s %s", it.getTokenType(), it.getTokenValue()))
                .orElse(null);
    }
}
