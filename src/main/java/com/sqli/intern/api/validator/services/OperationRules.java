package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.utilities.StringUtils;
import com.sqli.intern.api.validator.utilities.ValidatorUtility;
import com.sqli.intern.api.validator.utilities.enums.OperationTypeEnum;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.*;

public final class OperationRules {

    public static boolean isBodyNull(String body) {
        if (!StringUtils.isBlank(body))
            throw new OperationException(BODY_NULL);
        return true;
    }

    public static boolean isExpectedResponseNull(String expectedResponse) {
        if (StringUtils.isBlank(expectedResponse))
            throw new OperationException(EXPECTED_RESPONSE_NULL);
        return true;
    }

    public static boolean isJsonNotValid(String value) {
        if (!ValidatorUtility.isJson(value))
            throw new OperationException(NOT_VALID_JSON);
        return true;

    }


    public static boolean isHttpMethodValid(String httpMethod) {
        return OperationTypeEnum.isTypeGet(httpMethod) || OperationTypeEnum.isTypePost(httpMethod)
                || OperationTypeEnum.isTypePut(httpMethod) || OperationTypeEnum.isTypeDelete(httpMethod);
    }

    public static boolean isHttpMethodNotValid(String httpMethod) {
        if (!isHttpMethodValid(httpMethod))
            throw new OperationException(NOT_VALID_HTTP_METHOD);
        return true;
    }

    public static boolean isExpectedResponseTypeNotValid(String expectedResponse) {
        if (!(isExpectedResponseTypeValid(expectedResponse)))
            throw new OperationException(NOT_VALID_EXPECTED_RESPONSE_TYPE);
        return true;
    }

    public static boolean isExpectedResponseTypeValid(String expectedResponse) {
        return ValidatorUtility.isString(expectedResponse)
                || ValidatorUtility.isBoolean(expectedResponse)
                || ValidatorUtility.isNumber(expectedResponse)
                || ValidatorUtility.isJson(expectedResponse)
                || ValidatorUtility.isVoid(expectedResponse);
    }
}
