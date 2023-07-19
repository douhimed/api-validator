package com.sqli.intern.api.validator.utilities.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum ExpectedTypeEnum {
    STRING, BOOLEAN, NUMBER, JSON, VOID;

    private static final List<ExpectedTypeEnum> VALID_POST_EXPECTED_TYPE;
    private static final List<ExpectedTypeEnum> VALID_PUT_EXPECTED_TYPE;
    private static final List<ExpectedTypeEnum> VALID_GET_EXPECTED_TYPE;
    private static final List<ExpectedTypeEnum> VALID_DELETE_EXPECTED_TYPE;

    static {
        VALID_POST_EXPECTED_TYPE = new ArrayList<>();
        VALID_POST_EXPECTED_TYPE.add(NUMBER);
        VALID_POST_EXPECTED_TYPE.add(STRING);
        VALID_POST_EXPECTED_TYPE.add(JSON);

        VALID_PUT_EXPECTED_TYPE = new ArrayList<>();
        VALID_PUT_EXPECTED_TYPE.add(NUMBER);
        VALID_PUT_EXPECTED_TYPE.add(STRING);
        VALID_PUT_EXPECTED_TYPE.add(JSON);

        VALID_GET_EXPECTED_TYPE = new ArrayList<>();
        VALID_GET_EXPECTED_TYPE.add(NUMBER);
        VALID_GET_EXPECTED_TYPE.add(STRING);
        VALID_GET_EXPECTED_TYPE.add(JSON);
        VALID_GET_EXPECTED_TYPE.add(BOOLEAN);

        VALID_DELETE_EXPECTED_TYPE = new ArrayList<>();
        VALID_DELETE_EXPECTED_TYPE.add(VOID);
    }

    public static boolean estPostTypeValid(String type) {
        return StringUtils.isNotBlank(type)
                && VALID_POST_EXPECTED_TYPE.contains(OperationTypeEnum.valueOf(type.toUpperCase()));
    }

    public static boolean estPutTypeValid(String type) {
        return StringUtils.isNotBlank(type)
                && VALID_PUT_EXPECTED_TYPE.contains(OperationTypeEnum.valueOf(type.toUpperCase()));
    }

    public static boolean estGetTypeValid(String type) {
        return StringUtils.isNotBlank(type)
                && VALID_GET_EXPECTED_TYPE.contains(OperationTypeEnum.valueOf(type.toUpperCase()));
    }

    public static boolean estDeleteTypeValid(String type) {
        return StringUtils.isNotBlank(type)
                && VALID_DELETE_EXPECTED_TYPE.contains(OperationTypeEnum.valueOf(type.toUpperCase()));
    }

}

