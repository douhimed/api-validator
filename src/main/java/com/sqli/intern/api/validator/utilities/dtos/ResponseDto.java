package com.sqli.intern.api.validator.utilities.dtos;

import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String httpStatus;
    private String actualResponse;
    private String expectedType;

    private List<ReportDto> messages;
    private ValidationStatus validationStatus;

    public void addMessage(ReportDto reportDto) {
        initMessagesIfNull();
        this.messages.add(reportDto);
    }

    private void initMessagesIfNull() {
        if (messages == null) {
            messages = new ArrayList<>();
        }
    }
}
