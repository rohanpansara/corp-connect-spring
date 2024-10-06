package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum UserStatus{
    INTERN("Intern"),
    CONTRACT("Contract"),
    FULL_TIME("Full Time"),
    NOTICE_PERIOD("Notice Period");

    private final String label;

    UserStatus(String label) {
        this.label = label;
    }

    public static UserStatus getDefault() {
        return UserStatus.FULL_TIME;
    }

    public static UserStatus getUserStatus(Integer ordinal){
        return UserStatus.values()[ordinal];
    }
}
