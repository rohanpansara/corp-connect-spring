package com.jwtauthentication.enumerations;

import com.jwtauthentication.exceptions.hr.HolidayNotFoundException;
import com.jwtauthentication.utils.EssConstants;
import lombok.Getter;

@Getter
public enum HolidayType {
    PUBLIC("Public"),
    NATIONAL("National"),
    COMPANY("Company");

    private final String label;

    HolidayType(String label) {
        this.label = label;
    }

    public static String getDefault() {
        return HolidayType.PUBLIC.getLabel();
    }

    public static HolidayType getByOrdinal(Integer ordinal){
        return HolidayType.values()[ordinal];
    }

    public static HolidayType getByLabel(String label) {
        for (HolidayType type : values()) {
            if (type.getLabel().equalsIgnoreCase(label)) {
                return type;
            }
        }
        throw new HolidayNotFoundException(EssConstants.Holiday.TYPE_NOT_FOUND);
    }
}
