package com.jwtauthentication.exceptions.client;

import com.jwtauthentication.exceptions.common.BaseException;

public class InternalServerException extends BaseException {

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
