package com.sqli.intern.api.validator.utilities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sqli.intern.api.validator.utilities.validation.annotations.HttpMethodValid;
import com.sqli.intern.api.validator.utilities.validation.annotations.JsonValid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {
    @NotBlank(message = "Url must not be blank")
    private String url;

    @HttpMethodValid
    private String type;

    @NotBlank(message = "Body must not be blank")
    @JsonValid(message = "Body not valid")
    private String body;
    @JsonValid
    private String expectedResponse;
    private String actualResponse;
    private String expectedType;

    @JsonIgnore
    private Long projectId;
}


