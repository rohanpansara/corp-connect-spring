package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.dtos.hr.WorkShiftDTO;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.entities.hr.WorkShift;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class WorkShiftMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract WorkShift toEntity(WorkShiftDTO workShiftDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract WorkShiftDTO toDTO(WorkShift workShift);

    public abstract List<WorkShift> toEntityList(List<WorkShiftDTO> workShiftDTOList);

    public abstract List<WorkShiftDTO> toDTOList(List<WorkShift> workShiftList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(WorkShiftDTO workShiftDTO, @MappingTarget WorkShift workShift);

    @AfterMapping
    protected void dateFormatting(WorkShift workShift, @MappingTarget WorkShiftDTO workShiftDTO) throws BaseException {
        workShiftDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(workShift.getCreatedDate()));
        workShiftDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(workShift.getLastUpdatedDate()));
    }
}
