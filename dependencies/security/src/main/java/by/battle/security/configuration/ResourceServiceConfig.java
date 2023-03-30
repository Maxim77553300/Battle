package by.battle.security.configuration;

import by.battle.security.converter.AuthUserAuthenticationConverter;
import by.battle.security.errorhandler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Lazy
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {

    private static final String[] WHITE_LIST = {
            "/users/login",
            "/users/register",
            "/oauth/token"};
    private final by.battle.security.converter.AuthUserAuthenticationConverter authenticationConverter;
    private final CustomAuthenticationEntryPoint authEntryPoint;

    /**
     * REQUIRED: Jwt public key must be in your service properties! For example:
     * application.yml: 'security.jwt.publicKey = -----BEGIN PUBLIC KEY-----someSecuredPublicKey-----END PUBLIC KEY-----'
     */
    private final String publicJwtKey;

    public ResourceServiceConfig(AuthUserAuthenticationConverter authenticationConverter,
                                 CustomAuthenticationEntryPoint authEntryPoint,
                                 @Value("${security.jwt.publicKey}") String publicJwtKey) {
        this.authenticationConverter = authenticationConverter;
        this.authEntryPoint = authEntryPoint;
        this.publicJwtKey = publicJwtKey;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(authEntryPoint);
        resources.tokenStore(tokenStore());
    }

    @Bean
    @ConditionalOnMissingBean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        customizeJwtAccessTokenConverter(converter);

        return converter;
    }

    private void customizeJwtAccessTokenConverter(JwtAccessTokenConverter converter) {
        converter.setVerifierKey(publicJwtKey);
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(authenticationConverter);
    }
}
