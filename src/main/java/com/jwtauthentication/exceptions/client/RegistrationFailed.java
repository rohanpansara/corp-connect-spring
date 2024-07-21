package com.jwtauthentication.exceptions.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwtauthentication.exceptions.common.BaseException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationFailed extends BaseException {

    public RegistrationFailed(String message) {
        super(message);
    }

    public RegistrationFailed(String message, String errorCode) {
        super(message, errorCode);
    }

    public RegistrationFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationFailed(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
