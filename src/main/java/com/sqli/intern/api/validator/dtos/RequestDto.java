package com.sqli.intern.api.validator.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    private String url;
    private String type;
    private String body;
    private String expectedResponse;

    public boolean isOfType(String type) {
        if (type != null) {
            return type.equalsIgnoreCase("get") || type.equalsIgnoreCase("post") || type.equalsIgnoreCase("put") || type.equalsIgnoreCase("delete");
        }
        return false;
    }

}
