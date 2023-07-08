package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.exceptions.OperationException;
import com.sqli.intern.api.validator.utils.enums.ExceptionMessageEnum;
import com.sqli.intern.api.validator.utils.enums.OperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyWs {

    @Autowired
    private Caller getRequestHandler;

    public Caller getCaller(String type) {
        if (OperationTypeEnum.isTypeGet(type))
            return getRequestHandler;
        throw new OperationException(ExceptionMessageEnum.OPERATION_NOT_ELIGIBLE.getMessage());
    }

}
