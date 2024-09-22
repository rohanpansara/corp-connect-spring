package com.employee_self_service.mappers.hr;

import com.employee_self_service.dtos.hr.ShiftScheduleDTO;
import com.employee_self_service.entities.hr.ShiftSchedule;
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
    public abstract ShiftSchedule toEntity(ShiftScheduleDTO shiftScheduleDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract ShiftScheduleDTO toDTO(ShiftSchedule shiftSchedule);

    public abstract List<ShiftSchedule> toEntityList(List<ShiftScheduleDTO> shiftScheduleDTOList);

    public abstract List<ShiftScheduleDTO> toDTOList(List<ShiftSchedule> shiftScheduleList);

    @AfterMapping
    protected void dateFormatting(ShiftSchedule shiftSchedule, @MappingTarget ShiftScheduleDTO shiftScheduleDTO) throws BaseException {
        shiftScheduleDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(shiftSchedule.getCreatedDate()));
        shiftScheduleDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(shiftSchedule.getLastUpdatedDate()));
    }
}
