package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

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
