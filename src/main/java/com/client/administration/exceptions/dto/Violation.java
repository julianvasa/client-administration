package com.client.administration.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Simple POJO to hold the bean validation error that will be sent to the client performing the request
 */
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class Violation {
    private final String dateStamp;
    private final String field;
    private final String message;
}

