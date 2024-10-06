package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.JobTitlesDTO;
import com.corpConnect.entities.hr.JobTitles;
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
    public abstract JobTitles toEntity(JobTitlesDTO jobTitlesDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract JobTitlesDTO toDTO(JobTitles jobTitles);

    public abstract List<JobTitles> toEntityList(List<JobTitlesDTO> jobTitlesDTOList);

    public abstract List<JobTitlesDTO> toDTOList(List<JobTitles> jobTitlesList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(JobTitlesDTO jobTitlesDTO, @MappingTarget JobTitles jobTitles);

    @AfterMapping
    protected void dateFormatting(JobTitles jobTitles, @MappingTarget JobTitlesDTO jobTitlesDTO) {
        jobTitlesDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(jobTitles.getCreatedDate()));
        jobTitlesDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(jobTitles.getLastUpdatedDate()));
    }
}
