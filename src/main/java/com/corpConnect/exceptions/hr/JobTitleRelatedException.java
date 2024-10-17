package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class JobTitleRelatedException extends BaseException {
    public JobTitleRelatedException(String message) {
        super(message);
    }

    public JobTitleRelatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public JobTitleRelatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobTitleRelatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
