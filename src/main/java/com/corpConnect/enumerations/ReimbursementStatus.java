package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
public enum ReimbursementStatus {
    PENDING("Pending"),
    UNDER_SCRUTINY("Under Scrutiny"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String label;

    ReimbursementStatus(String label) {
        this.label = label;
    }

    public static ReimbursementStatus getDefault() {
        return ReimbursementStatus.PENDING;
    }

    public static ReimbursementStatus getAccountStatus(Integer ordinal){
        return ReimbursementStatus.values()[ordinal];
    }
}
