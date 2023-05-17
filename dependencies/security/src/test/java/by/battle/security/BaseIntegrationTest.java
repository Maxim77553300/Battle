package by.battle.security;

import by.battle.security.utils.AuthTestClaims;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

@AutoConfigureMockMvc
@SpringBootTest(properties = {
        "spring.cloud.config.enabled=false",
        "spring.cloud.discovery.enabled=false"})
public class BaseIntegrationTest {

    private static final String AUTHORISATION = "Authorization";

    private static final String BEARER = "Bearer ";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    @BeforeEach
    public void disableTokenVerifying() {
        JwtAccessTokenConverter jwtAccessTokenConverter =
                (JwtAccessTokenConverter) ReflectionTestUtils.getField(jwtTokenStore, "jwtTokenEnhancer");

        assert jwtAccessTokenConverter != null;

        jwtAccessTokenConverter.setVerifier(getVerifierImpl(jwtAccessTokenConverter));
    }

    protected MockHttpServletRequestBuilder authorized(MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                                       AuthTestClaims claims) {
        return mockHttpServletRequestBuilder.header(AUTHORISATION, BEARER + createToken(claims));
    }

    private String createToken(AuthTestClaims claims) {
        Signer signer = (Signer) ReflectionTestUtils.getField(jwtAccessTokenConverter, "signer");
        String claimsAsString = serializeObject(claims);
        Map<String, String> header = new HashMap<>();

        assert signer != null;

        return JwtHelper.encode(claimsAsString, signer, header).getEncoded();
    }

    private SignatureVerifier getVerifierImpl(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new SignatureVerifier() {
            @Override
            public void verify(byte[] content, byte[] signature) {

            }

            @Override
            public String algorithm() {
                SignatureVerifier signatureVerifier =
                        (SignatureVerifier) ReflectionTestUtils.getField(jwtAccessTokenConverter, "verifier");

                assert signatureVerifier != null;

                return signatureVerifier.algorithm();
            }
        };
    }


    private String serializeObject(AuthTestClaims claims) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(claims);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
