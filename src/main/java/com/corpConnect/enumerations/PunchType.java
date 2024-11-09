package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum PunchType {
    FIRST_IN("Present"),
    BREAK("Absent"),
    LAST_OUT("Under Review");

    private final String label;

    PunchType(String label) {
        this.label = label;
    }

    public static PunchType getDefault() {
        return PunchType.FIRST_IN;
    }

    public static PunchType getAccountStatus(Integer ordinal){
        return PunchType.values()[ordinal];
    }
}
