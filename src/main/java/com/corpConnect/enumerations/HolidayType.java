package com.corpConnect.enumerations;

import com.corpConnect.exceptions.hr.HolidayRelatedException;
import com.corpConnect.utils.constants.MessageConstants;
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
        throw new HolidayRelatedException(MessageConstants.Holiday.TYPE_NOT_FOUND);
    }
}
