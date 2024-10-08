package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class WorkShiftNotFoundException extends BaseException {
    public WorkShiftNotFoundException(String message) {
        super(message);
    }

    public WorkShiftNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public WorkShiftNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkShiftNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
