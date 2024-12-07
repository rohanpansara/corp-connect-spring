package com.corpConnect.dtos.card.dashboard;

import com.corpConnect.dtos.card.BaseCardDTO;
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
public class MonthlyAttendanceCardDTO extends BaseCardDTO {
    private String totalValue;
    private boolean absent;

    @Autowired
    public MonthlyAttendanceCardDTO(String title, String value, String description, String totalValue, boolean absent) {
        super(title, value, description);
        this.totalValue = totalValue;
        this.absent = absent;
    }
}