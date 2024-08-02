package com.jwtauthentication.enumerations;

import lombok.Getter;

@Getter
public enum PasswordStatus {
    VALID("Valid"),
    EXPIRED("Expired");

    private final String label;

    PasswordStatus(String label) {
        this.label = label;
    }

    public static String getDefault() {
        return PasswordStatus.VALID.getLabel();
    }

    public static PasswordStatus getPasswordStatus(Integer ordinal){
        return PasswordStatus.values()[ordinal];
    }
}
