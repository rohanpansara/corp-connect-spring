package com.corpConnect.exceptions.client;

import com.corpConnect.exceptions.common.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRelatedException extends BaseException {

    public UserRelatedException(String message) {
        super(message);
    }

    public UserRelatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UserRelatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRelatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
