package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT("Absent"),
    UNDER_REVIEW("Under Review");

    private final String label;

    AttendanceStatus(String label) {
        this.label = label;
    }

    public static AttendanceStatus getDefault() {
        return AttendanceStatus.PRESENT;
    }

    public static AttendanceStatus getAccountStatus(Integer ordinal){
        return AttendanceStatus.values()[ordinal];
    }
}
