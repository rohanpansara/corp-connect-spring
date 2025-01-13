package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum AttendancePeriod {
    FULL_DAY("Full Day"),
    FIRST_HALF("First Half"),
    SECOND_HALF("Second Half");

    private final String label;

    AttendancePeriod(String label) {
        this.label = label;
    }

    public static AttendancePeriod getDefault() {
        return AttendancePeriod.FIRST_HALF;
    }

    public static AttendancePeriod getAttendancePeriod(Integer ordinal) {
        return AttendancePeriod.values()[ordinal];
    }
}
