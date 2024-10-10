package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.entities.hr.Department;
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
public abstract class DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract Department toEntity(DepartmentDTO departmentDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract DepartmentDTO toDTO(Department department);

    public abstract List<Department> toEntityList(List<DepartmentDTO> departmentDTOList);

    public abstract List<DepartmentDTO> toDTOList(List<Department> departmentList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(DepartmentDTO departmentDTO, @MappingTarget Department department);

    @AfterMapping
    protected void dateFormatting(Department department, @MappingTarget DepartmentDTO departmentDTO) throws BaseException {
        departmentDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(department.getCreatedDate()));
        departmentDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(department.getLastUpdatedDate()));
    }

}
