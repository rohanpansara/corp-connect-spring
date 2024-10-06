package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class JobTitleNotFoundException extends BaseException {
    public JobTitleNotFoundException(String message) {
        super(message);
    }

    public JobTitleNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public JobTitleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobTitleNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
