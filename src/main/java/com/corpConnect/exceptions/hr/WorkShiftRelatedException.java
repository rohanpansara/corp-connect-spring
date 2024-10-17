package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class WorkShiftRelatedException extends BaseException {
    public WorkShiftRelatedException(String message) {
        super(message);
    }

    public WorkShiftRelatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public WorkShiftRelatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkShiftRelatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
