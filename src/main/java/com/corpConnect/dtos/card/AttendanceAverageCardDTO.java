package com.corpConnect.dtos.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendanceAverageCardDTO extends CardDataDTO {
    private boolean isOnTime;

    @Autowired
    public AttendanceAverageCardDTO(String title, String value, String description, boolean isOnTime) {
        super(title, value, description);
        this.isOnTime = isOnTime;
    }
}

