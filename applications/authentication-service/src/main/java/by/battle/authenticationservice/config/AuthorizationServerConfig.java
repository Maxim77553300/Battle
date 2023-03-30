package by.battle.authenticationservice.config;


import by.battle.authenticationservice.converter.UserAuthenticationTokenConverter;
import by.battle.security.converter.AuthUserAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${keystore.path}")
    private String jksClasspath;
    @Value("${keystore.password}")
    private String jksPassword;
    @Value("${keystore.keypair-name}")
    private String keypairName;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final AuthUserAuthenticationConverter authenticationConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode(EMPTY))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("scope");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();
        var jwtAccessTokenEnhancer = new UserAuthenticationTokenConverter();
        var defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(authenticationConverter);
        jwtAccessTokenEnhancer.setAccessTokenConverter(defaultAccessTokenConverter);
        converter.setAccessTokenConverter(jwtAccessTokenEnhancer);
        converter.setKeyPair(getKeyPair());
        return converter;
    }

    private KeyPair getKeyPair() {
        ClassPathResource ksFile = new ClassPathResource(jksClasspath);
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, jksPassword.toCharArray());
        return ksFactory.getKeyPair(keypairName);
    }
}
