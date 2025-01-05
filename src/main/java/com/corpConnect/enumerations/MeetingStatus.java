package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum MeetingStatus {
    SCHEDULED("Scheduled"),
    DELAYED("Delayed"),
    CANCELLED("Cancelled"),
    OVER("Over");

    private final String label;

    MeetingStatus(String label) {
        this.label = label;
    }

    public static String getDefault() {
        return MeetingStatus.SCHEDULED.getLabel();
    }

    public static MeetingStatus getByOrdinal(Integer ordinal){
        return MeetingStatus.values()[ordinal];
    }

}