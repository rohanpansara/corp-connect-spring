package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.entities.hr.JobTitle;
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
public abstract class JobTitlesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract JobTitle toEntity(JobTitleDTO jobTitleDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract JobTitleDTO toDTO(JobTitle jobTitle);

    public abstract List<JobTitle> toEntityList(List<JobTitleDTO> jobTitleDTOList);

    public abstract List<JobTitleDTO> toDTOList(List<JobTitle> jobTitleList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(JobTitleDTO jobTitleDTO, @MappingTarget JobTitle jobTitle);

    @AfterMapping
    protected void dateFormatting(JobTitle jobTitle, @MappingTarget JobTitleDTO jobTitleDTO) {
        jobTitleDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(jobTitle.getCreatedDate()));
        jobTitleDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(jobTitle.getLastUpdatedDate()));
    }
}
