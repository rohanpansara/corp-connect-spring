package com.corpConnect.exceptions.client;

import com.corpConnect.exceptions.common.BaseException;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
