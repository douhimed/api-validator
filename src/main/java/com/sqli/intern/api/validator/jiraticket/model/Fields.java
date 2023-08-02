package com.sqli.intern.api.validator.jiraticket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Fields {
    private static final String NULL_VALUE = "<null>";

    @JsonProperty("project")
    private Project project;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("description")
    private String description;
    @JsonProperty("issuetype")
    private Issuetype issuetype;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Fields.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        appendField(sb, "project", this.project);
        appendField(sb, "summary", this.summary);
        appendField(sb, "description", this.description);
        appendField(sb, "issuetype", this.issuetype);
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    private void appendField(StringBuilder sb, String fieldName, Object value) {
        sb.append(fieldName).append('=').append(value == null ? NULL_VALUE : value).append(',');
    }

}
