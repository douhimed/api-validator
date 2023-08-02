package com.sqli.intern.api.validator.jiraTicket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Issuetype {

    @JsonProperty("id")
    private String id;

    @Override
    public String toString() {
        return "Issuetype [id=" + id + "]";
    }

}
