package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum LeaveLogType {

    MANUAL_CORRECTION("Manual Leave Correction"),
    LEAVE_TAKEN("Leave Taken"),
    LEAVE_ACCRUAL("Leave Accrual"),
    LEAVE_LAPSED("Leave Lapsed");

    private final String label;

    LeaveLogType(String label) {
        this.label = label;
    }

    public static LeaveLogType getDefault() {
        return LeaveLogType.MANUAL_CORRECTION;
    }

    public static LeaveLogType getLeaveLogType(Integer ordinal) {
        return LeaveLogType.values()[ordinal];
    }

}
