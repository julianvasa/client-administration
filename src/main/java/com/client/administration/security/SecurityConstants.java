package com.client.administration.security;

/**
 * Some security constants
 *
 * @author julianvasa
 */
class SecurityConstants {
    static final String SECRET_KEY = "CJvNWyiUuxb7Zqa1F9FYQ34tKjVtdNzLX90YtWaqRaosdlYQxrd408JWljdQuUE";
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
}
