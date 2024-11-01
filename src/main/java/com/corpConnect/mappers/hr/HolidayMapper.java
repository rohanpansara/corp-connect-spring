package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.HolidayDTO;
import com.corpConnect.entities.hr.Holiday;
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
public abstract class HolidayMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract Holiday toEntity(HolidayDTO holidayDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract HolidayDTO toDTO(Holiday holiday);

    public abstract List<Holiday> toEntityList(List<HolidayDTO> holidayDTOList);
    public abstract List<HolidayDTO> toDTOList(List<Holiday> holidayList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(HolidayDTO holidayDTO, @MappingTarget Holiday holiday);

    @AfterMapping
    protected void dateFormatting(Holiday holiday, @MappingTarget HolidayDTO holidayDTO) {
        holidayDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(holiday.getCreatedDate()));
        holidayDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(holiday.getLastUpdatedDate()));
        holidayDTO.setDate(CustomDateTimeFormatter.getLocalDateString(holiday.getDate()));
    }
}
