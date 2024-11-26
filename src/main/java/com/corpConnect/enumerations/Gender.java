package com.corpConnect.enumerations;

import lombok.Getter;

@Getter
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

}

