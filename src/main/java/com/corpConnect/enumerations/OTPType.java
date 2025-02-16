package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum OTPType {

    NEW_USER_VERIFICATION("New User Verification"),
    FORGOT_PASSWORD_VERIFICATION("Forgot Password Verification");

    private final String label;

    OTPType(String label) {
        this.label = label;
    }

    public static OTPType getDefault() {
        return OTPType.NEW_USER_VERIFICATION;
    }

    public static OTPType getLeaveLogType(Integer ordinal) {
        return OTPType.values()[ordinal];
    }

}
