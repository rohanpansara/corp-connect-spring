package com.employee_self_service.mappers.hr;

import com.employee_self_service.dtos.hr.ShiftScheduleDTO;
import com.employee_self_service.entities.hr.WorkShifts;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.utils.CustomDateTimeFormatter;
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
    public abstract WorkShifts toEntity(ShiftScheduleDTO shiftScheduleDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract ShiftScheduleDTO toDTO(WorkShifts workShifts);

    public abstract List<WorkShifts> toEntityList(List<ShiftScheduleDTO> shiftScheduleDTOList);

    public abstract List<ShiftScheduleDTO> toDTOList(List<WorkShifts> workShiftsList);

    @AfterMapping
    protected void dateFormatting(WorkShifts workShifts, @MappingTarget ShiftScheduleDTO shiftScheduleDTO) throws BaseException {
        shiftScheduleDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(workShifts.getCreatedDate()));
        shiftScheduleDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(workShifts.getLastUpdatedDate()));
    }
}
