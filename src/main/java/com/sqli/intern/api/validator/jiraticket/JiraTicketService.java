package com.sqli.intern.api.validator.jiraticket;

import java.nio.charset.StandardCharsets;

import com.sqli.intern.api.validator.jiraticket.model.JiraPayload;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class JiraTicketService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${jira.user-name}")
    private String username;
    @Value("${jira.secret}")
    private String secret;
    @Value("${jira.base-url}")
    private String baseUrl;
    @Value("${jira.ticket-url}")
    private String ticketCreationUrl;


    public String createJiraTicket(JiraPayload jiraPayload) {
        ResponseEntity<String> response = restTemplate.exchange(baseUrl.concat(ticketCreationUrl),
                HttpMethod.POST,
                new HttpEntity<>(jiraPayload, getHeaders()),
                String.class);
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return response.getBody();
        } else {
            return "Error: Unable to create Jira ticket. Status code: " + statusCode.value();
        }
    }


    public HttpHeaders getHeaders() {
        String auth = username + ":" + secret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        return headers;
    }

}
