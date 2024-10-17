package com.corpConnect.exceptions.hr;

import com.corpConnect.exceptions.common.BaseException;

public class MeetingRoomRelatedException extends BaseException {
    public MeetingRoomRelatedException(String message) {
        super(message);
    }

    public MeetingRoomRelatedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public MeetingRoomRelatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetingRoomRelatedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
