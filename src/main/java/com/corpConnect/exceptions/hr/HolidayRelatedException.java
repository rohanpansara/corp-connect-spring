package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class HolidayRelatedException extends BaseException {
    public HolidayRelatedException(String message) {
        super(message);
    }

    public HolidayRelatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public HolidayRelatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HolidayRelatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
