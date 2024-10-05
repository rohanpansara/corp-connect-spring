package com.employee_self_service.mappers.hr;

import com.employee_self_service.dtos.hr.WorkShiftsDTO;
import com.employee_self_service.entities.hr.WorkShifts;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class ShiftScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract WorkShifts toEntity(WorkShiftsDTO workShiftsDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract WorkShiftsDTO toDTO(WorkShifts workShifts);

    public abstract List<WorkShifts> toEntityList(List<WorkShiftsDTO> workShiftsDTOList);

    public abstract List<WorkShiftsDTO> toDTOList(List<WorkShifts> workShiftsList);

    @AfterMapping
    protected void dateFormatting(WorkShifts workShifts, @MappingTarget WorkShiftsDTO workShiftsDTO) throws BaseException {
        workShiftsDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(workShifts.getCreatedDate()));
        workShiftsDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(workShifts.getLastUpdatedDate()));
    }
}
