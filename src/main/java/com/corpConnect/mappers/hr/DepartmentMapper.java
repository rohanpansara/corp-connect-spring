package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.entities.hr.Department;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.UserNotFoundException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.repositories.user.UserRepository;
import com.corpConnect.services.hr.impl.DepartmentServiceImpl;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class DepartmentMapper {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentMapper.class);

    @Autowired
    private UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "departmentHead", ignore = true)
    public abstract Department toEntity(DepartmentDTO departmentDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "departmentHead", source = "departmentHead")
    public abstract DepartmentDTO toDTO(Department department);

    public abstract List<Department> toEntityList(List<DepartmentDTO> departmentDTOList);

    public abstract List<DepartmentDTO> toDTOList(List<Department> departmentList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(DepartmentDTO departmentDTO, @MappingTarget Department department);

    @AfterMapping
    protected void setDepartmentHead(DepartmentDTO departmentDTO, @MappingTarget Department department) {

        if (departmentDTO.getDepartmentHead() == null || departmentDTO.getDepartmentHead().getId() == null) {
            logger.warn("Department head or department head ID is null while mapping.");
            return;
        }

        User departmentHead = userRepository.findById(departmentDTO.getDepartmentHead().getId())
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("User", "set", "ID", departmentDTO.getDepartmentHead().getId(), "inside after mapping"));
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                });

        department.setDepartmentHead(departmentHead);
    }

    @AfterMapping
    protected void dateFormatting(Department department, @MappingTarget DepartmentDTO departmentDTO) throws BaseException {
        departmentDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(department.getCreatedDate()));
        departmentDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(department.getLastUpdatedDate()));
    }

}
