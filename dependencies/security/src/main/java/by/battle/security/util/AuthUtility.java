package by.battle.security.util;

import by.battle.common.RoleName;
import by.battle.security.user.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class AuthUtility {

    public static String getCurrentUserId() {
        return ((AuthUser) getCurrentAuthentication().getPrincipal()).getUserId();
    }

    public static List<RoleName> getCurrentRoleNames() {
        return AuthorityUtils.authorityListToSet(getCurrentAuthentication().getAuthorities())
                .stream().map(RoleName::valueOf).collect(Collectors.toList());
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

    private static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
