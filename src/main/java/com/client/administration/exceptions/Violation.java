package com.client.administration.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
class Violation {
    private final String dateStamp;
    private final String field;
    private final String message;
}

