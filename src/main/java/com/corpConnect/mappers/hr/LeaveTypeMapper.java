package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.LeaveTypeDTO;
import com.corpConnect.entities.hr.LeaveType;
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
public abstract class LeaveTypeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract LeaveType toEntity(LeaveTypeDTO leaveTypeDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract LeaveTypeDTO toDTO(LeaveType leaveType);

    public abstract List<LeaveType> getEntityList(List<LeaveTypeDTO> leaveTypeDTOList);

    public abstract List<LeaveTypeDTO> getDTOList(List<LeaveType> leaveTypeList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(LeaveTypeDTO leaveTypeDTO, @MappingTarget LeaveType leaveType);

    @AfterMapping
    protected void dateFormatting(LeaveType leaveType, @MappingTarget LeaveTypeDTO leaveTypeDTO) {
        leaveTypeDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(leaveType.getCreatedDate()));
        leaveTypeDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(leaveType.getLastUpdatedDate()));
    }
}
