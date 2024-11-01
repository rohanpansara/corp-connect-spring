package com.corpConnect.utils.filters;

import com.corpConnect.enumerations.HolidayType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@Component
public class HolidayFilter {
    private List<Integer> holidayIds;
    private String holidayName;
    private LocalDate holidayBetweenStartDate;
    private LocalDate holidayBetweenEndDate;
    private String holidayDescription;
    private List<HolidayType> holidayTypes;
    private Boolean isRecurring;
}
