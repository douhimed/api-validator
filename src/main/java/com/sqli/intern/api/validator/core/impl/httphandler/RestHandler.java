package com.sqli.intern.api.validator.core.impl.httphandler;

import com.sqli.intern.api.validator.authentication.AuthHeaderProvider;
import com.sqli.intern.api.validator.core.RestCaller;
import com.sqli.intern.api.validator.core.impl.OperationHandler;
import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.AuthenticationType;
import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;
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
    public void invoke(ResponseDto responseDto) {
        try {
            AuthenticationType authType = AuthenticationType.BASIC;
            AuthHeaderProvider authHeaderProvider;
            if (authType == AuthenticationType.BASIC) {
                authHeaderProvider = new AuthHeaderProvider("username", "password");
            } else if (authType == AuthenticationType.JWT) {
                String jwtToken = "token";
                authHeaderProvider = new AuthHeaderProvider(jwtToken);
            } else {
                throw new IllegalArgumentException("Unsupported authentication type");
            }
            HttpHeaders headers = authHeaderProvider.createAuthHeader(authType);
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> responseEntity = restTemplate.exchange(responseDto.getUrl().trim(),
                    getType(),
                    new HttpEntity<>(getBody(responseDto), headers),
                    String.class);
            responseDto.setHttpStatus(String.valueOf(responseEntity.getStatusCode().value()));
            responseDto.setActualResponse(responseEntity.getBody());
            invokeNext(responseDto);
        } catch (HttpClientErrorException e) {
            String errorMessage = e.getStatusCode().is5xxServerError()
                    ? ExceptionMessageEnum.SERVICE_NOT_FOUND.getMessage()
                    : ExceptionMessageEnum.BAD_REQUEST.getMessage();

            responseDto.addMessage(ReportDto.createErrorMessage(errorMessage));
        }
    }
}