package com.employee_self_service.mappers.hr;

import com.employee_self_service.dtos.hr.HolidaysDTO;
import com.employee_self_service.entities.hr.Holidays;
import com.employee_self_service.utils.functions.CustomDateTimeFormatter;
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
    public abstract Holidays toEntity(HolidaysDTO holidaysDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract HolidaysDTO toDTO(Holidays holidays);

    public abstract List<Holidays> toEntityList(List<HolidaysDTO> holidaysDTOList);
    public abstract List<HolidaysDTO> toDTOList(List<Holidays> holidaysList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(HolidaysDTO holidaysDTO, @MappingTarget Holidays holidays);

    @AfterMapping
    protected void dateFormatting(Holidays holidays, @MappingTarget HolidaysDTO holidaysDTO) {
        holidaysDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(holidays.getCreatedDate()));
        holidaysDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(holidays.getLastUpdatedDate()));
        holidaysDTO.setDate(CustomDateTimeFormatter.getLocalDateString(holidays.getDate()));
    }
}
