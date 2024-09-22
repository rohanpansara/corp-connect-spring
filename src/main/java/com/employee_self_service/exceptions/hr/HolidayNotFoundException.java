package com.employee_self_service.exceptions.hr;

import com.employee_self_service.exceptions.common.BaseException;

public class HolidayNotFoundException extends BaseException {
    public HolidayNotFoundException(String message) {
        super(message);
    }

    public HolidayNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public HolidayNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HolidayNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
