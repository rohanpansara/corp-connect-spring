package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class MeetingRoomNotFoundException extends BaseException {
    public MeetingRoomNotFoundException(String message) {
        super(message);
    }

    public MeetingRoomNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public MeetingRoomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetingRoomNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
