package com.corpConnect.utils.filters;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class DepartmentFilter {
    private List<Integer> departmentIds;
    private String departmentName;
    private List<Integer> departmentHeadUserIds;
    private List<String> departmentCodes;
    private List<String> departmentLocations;
    private List<String> departmentPhoneExtensions;
}
