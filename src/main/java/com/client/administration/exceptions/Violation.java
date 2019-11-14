package com.client.administration.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Violation {
    private final String field;
    private final String message;
}

