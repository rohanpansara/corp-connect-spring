package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class DepartmentRelatedException extends BaseException {
    public DepartmentRelatedException(String message) {
        super(message);
    }

    public DepartmentRelatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public DepartmentRelatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentRelatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
