package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.DepartmentsDTO;
import com.corpConnect.entities.hr.Departments;
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
public abstract class DepartmentsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract Departments toEntity(DepartmentsDTO departmentsDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract DepartmentsDTO toDTO(Departments departments);

    public abstract List<Departments> toEntityList(List<DepartmentsDTO> departmentsDTOList);

    public abstract List<DepartmentsDTO> toDTOList(List<Departments> departmentsList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(DepartmentsDTO departmentsDTO, @MappingTarget Departments departments);

    @AfterMapping
    protected void dateFormatting(Departments departments, @MappingTarget DepartmentsDTO departmentsDTO) throws BaseException {
        departmentsDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(departments.getCreatedDate()));
        departmentsDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(departments.getLastUpdatedDate()));
    }

}
