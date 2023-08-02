package com.sqli.intern.api.validator.core.impl.httphandler;

import com.sqli.intern.api.validator.authentication.AuthHeaderProvider;
import com.sqli.intern.api.validator.core.RestCaller;
import com.sqli.intern.api.validator.core.impl.OperationHandler;
import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.entities.ProjectEntity;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;
import com.sqli.intern.api.validator.utilities.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public abstract class RestHandler extends OperationHandler implements RestCaller {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    protected JsonHandler commandValidator;

    @Autowired
    protected JsonHandler queryValidator;

    public abstract HttpMethod getType();

    public abstract HttpEntity getBody(ResponseDto responseDto);

    @Override
    public void runTest(ResponseDto responseDto) {
        invoke(responseDto);
    }

    @Override
    public void runTest(ResponseDto responseDto, AuthHeaderProvider authHeaderProvider) {
        invoke(responseDto, authHeaderProvider);
    }

    @Override
    public void invoke(ResponseDto responseDto) {
        invoke(responseDto, null);
    }

    @Override
    public void invoke(ResponseDto responseDto, AuthHeaderProvider authHeaderProvider) {
        try {
            ProjectEntity projectEntity = new ProjectEntity();
            if (projectEntity.isWithAuth()) {
                HttpHeaders headers;
                if (authHeaderProvider == null) {
                    headers = createDefaultHttpHeaders();
                } else {
                    headers = authHeaderProvider.createAuthHeader();
                }
                headers.setContentType(MediaType.APPLICATION_JSON);
                ResponseEntity<String> responseEntity = restTemplate.exchange(responseDto.getUrl().trim(),
                        getType(),
                        new HttpEntity<>(getBody(responseDto), headers),
                        String.class);
                responseDto.setHttpStatus(String.valueOf(responseEntity.getStatusCode().value()));
                responseDto.setActualResponse(responseEntity.getBody());
                invokeNext(responseDto);
            } else {
                throw new AuthenticationException(ExceptionMessageEnum.AUTHENTICATION_NOT_SUPPORTED.getMessage());
            }
        } catch (
                HttpClientErrorException e) {
            String errorMessage = e.getStatusCode().is5xxServerError()
                    ? ExceptionMessageEnum.SERVICE_NOT_FOUND.getMessage()
                    : ExceptionMessageEnum.BAD_REQUEST.getMessage();

            responseDto.addMessage(ReportDto.createErrorMessage(errorMessage));
        }
    }

    private HttpHeaders createDefaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic <base64-encoded-username-and-password>");
        return headers;
    }
}