package com.sqli.intern.api.validator.authentication;

import java.nio.charset.StandardCharsets;

import com.sqli.intern.api.validator.utilities.enums.AuthenticationType;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class AuthHeaderProvider {
    private final String username;
    private final String password;
    private final String jwtToken;

    public AuthHeaderProvider(String username, String password) {
        this.username = username;
        this.password = password;
        this.jwtToken = null;
    }

    public AuthHeaderProvider(String jwtToken) {
        this.username = null;
        this.password = null;
        this.jwtToken = jwtToken;
    }

    public HttpHeaders createAuthHeader(AuthenticationType authType) {
        HttpHeaders headers = new HttpHeaders();
        if (authType == AuthenticationType.BASIC) {
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.set("Authorization", authHeader);
        } else if (authType == AuthenticationType.JWT) {
            assert jwtToken != null;
            headers.setBearerAuth(jwtToken);
        }
        return headers;
    }
}

