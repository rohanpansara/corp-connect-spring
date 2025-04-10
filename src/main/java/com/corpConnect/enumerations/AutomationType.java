package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum AutomationType {
    EMAIL("Email"),
    NOTIFICATION("Notification");

    private final String label;

    AutomationType(String label) {
        this.label = label;
    }

    public static AutomationType getDefault() {
        return AutomationType.NOTIFICATION;
    }

    public static AutomationType getAccountStatus(Integer ordinal) {
        return AutomationType.values()[ordinal];
    }
}
