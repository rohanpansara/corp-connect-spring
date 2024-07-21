package com.jwtauthentication.exceptions.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwtauthentication.exceptions.common.BaseException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginFailed extends BaseException {

    public LoginFailed(String message) {
        super(message);
    }

    public LoginFailed(String message, String errorCode) {
        super(message, errorCode);
    }

    public LoginFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailed(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
