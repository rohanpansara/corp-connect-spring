package com.corpConnect.exceptions.jwt;

import com.corpConnect.exceptions.common.BaseException;

public class JwtAuthenticationException extends BaseException {
    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, String errorCode) {
        super(message, errorCode);
    }

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtAuthenticationException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
