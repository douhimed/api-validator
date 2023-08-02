package com.sqli.intern.api.validator.authentication;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class AuthHeaderProvider {
    private final String username;
    private final String password;

    public AuthHeaderProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public HttpHeaders createAuthHeader() {
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }
}

