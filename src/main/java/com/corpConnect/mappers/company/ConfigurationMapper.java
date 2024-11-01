package com.corpConnect.mappers.company;

import com.corpConnect.dtos.company.ConfigurationDTO;
import com.corpConnect.entities.company.Configuration;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class ConfigurationMapper {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract Configuration toEntity(ConfigurationDTO configurationDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract ConfigurationDTO toDTO(Configuration configuration);

    public abstract List<Configuration> toEntityList(List<ConfigurationDTO> configurationDTOList);

    public abstract List<ConfigurationDTO> toDTOList(List<Configuration> configurationList);

    @AfterMapping
    protected void dateFormatting(Configuration configuration, @MappingTarget ConfigurationDTO configurationDTO) throws BaseException {
        configurationDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(configuration.getCreatedDate()));
        configurationDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(configuration.getLastUpdatedDate()));
    }
}
