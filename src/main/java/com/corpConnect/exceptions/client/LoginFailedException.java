package com.corpConnect.exceptions.client;

import com.corpConnect.exceptions.common.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginFailedException extends BaseException {

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
