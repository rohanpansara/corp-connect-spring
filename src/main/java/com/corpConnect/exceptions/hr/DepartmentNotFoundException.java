package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class DepartmentNotFoundException extends BaseException {
    public DepartmentNotFoundException(String message) {
        super(message);
    }

    public DepartmentNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public DepartmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
