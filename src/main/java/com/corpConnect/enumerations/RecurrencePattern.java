package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum RecurrencePattern {
    ONE_TIME("One Time"),
    DAILY("Daily"),
    WEEKLY("Weekly"),
    BIWEEKLY("Bi-Weekly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly"),
    HALF_YEARLY("Half Yearly"),
    YEARLY("Yearly");

    private final String label;

    RecurrencePattern(String label) {
        this.label = label;
    }

    public static RecurrencePattern getDefault() {
        return RecurrencePattern.DAILY;
    }

    public static RecurrencePattern getRecurrencePattern(Integer ordinal){
        return RecurrencePattern.values()[ordinal];
    }
}
