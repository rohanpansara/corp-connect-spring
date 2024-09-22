package com.employee_self_service.enumerations;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String label;

    Gender(String label) {
        this.label = label;
    }

    public static String getDefault() {
        return Gender.OTHER.getLabel();
    }

    public String getLabel() {
        return label;
    }
}

