package com.sqli.intern.api.validator.authentication;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class AuthHeaderProvider {
    private final String username;
    private final String password;

    public AuthHeaderProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void createAuthHeader(HttpHeaders headers) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
    }

    public void createDefaultHttpHeaders(HttpHeaders headers) {
        headers.set("Authorization", "Basic <base64-encoded-username-and-password>");
    }

    public HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (isAuthRequired()) createAuthHeader(headers);
        else createDefaultHttpHeaders(headers);
        return headers;
    }

    private boolean isAuthRequired() {
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password);
    }

}
