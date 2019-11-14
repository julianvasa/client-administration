package com.client.administration.model;

import lombok.Builder;
import lombok.Data;

/**
 * Simple POJO to hold the response to the client in case of errors
 *
 * @author Julian Vasa
 */
@Data
@Builder
public class Error {
    private String code;
    private String description;
}
