package com.jwtauthentication.enumerations;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ENABLED("Enabled"),
    LOCKED("Locked"),
    EXPIRED("Expired"),
    TERMINATED("Terminated");

    private final String label;

    AccountStatus(String label) {
        this.label = label;
    }

    public static AccountStatus getDefault() {
        return AccountStatus.ENABLED;
    }

    public static AccountStatus getAccountStatus(Integer ordinal){
        return AccountStatus.values()[ordinal];
    }
}
