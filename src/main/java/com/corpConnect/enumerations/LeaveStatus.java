package com.corpConnect.enumerations;

import com.corpConnect.exceptions.client.RecordNotFoundException;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.Getter;

@Getter
public enum LeaveStatus {

    PENDING("Pending"),
    REJECTED("Rejected"),
    ACCEPTED("Accepted");

    private final String label;

    LeaveStatus(String label) {
        this.label = label;
    }

    public static String getDefault() {
        return LeaveStatus.PENDING.getLabel();
    }

    public static LeaveStatus getByOrdinal(Integer ordinal){
        return LeaveStatus.values()[ordinal];
    }

    public static LeaveStatus getByLabel(String label) {
        for (LeaveStatus status : values()) {
            if (status.getLabel().equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new RecordNotFoundException(MessageConstants.Leave.LEAVE_STATUS_NOT_FOUND);
    }

}
