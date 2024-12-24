package com.corpConnect.mappers.user.userDetails;

import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.dtos.user.userDetails.DepartmentDetailDTO;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.entities.user.userDetails.DepartmentDetail;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class DepartmentDetailMapper {

    @Autowired
    private UserService userService;

    @Named("toEntity")
    public abstract DepartmentDetail toEntity(DepartmentDetailDTO departmentDetailDTO);

    @Named("toDTO")
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract DepartmentDetailDTO toDTO(DepartmentDetail departmentDetail);

    public abstract List<DepartmentDetail> toEntityList(List<DepartmentDetailDTO> departmentDetailDTOList);
    public abstract List<DepartmentDetailDTO> toDTOList(List<DepartmentDetail> departmentDetailList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(DepartmentDetailDTO departmentDetailDTO, @MappingTarget DepartmentDetail departmentDetail);

    @AfterMapping
    protected void stringFormatting(DepartmentDetail departmentDetail, @MappingTarget DepartmentDetailDTO departmentDetailDTO){
        departmentDetailDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(departmentDetail.getCreatedDate()));
        departmentDetailDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(departmentDetail.getLastUpdatedDate()));
        departmentDetailDTO.setCreatedBy(userService.getUsernameByUserId(departmentDetail.getUser().getId()));
        departmentDetailDTO.setLastUpdatedBy(userService.getUsernameByUserId(departmentDetail.getUser().getId()));
    }

}
