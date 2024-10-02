package com.employee_self_service.mappers.hr;

import com.employee_self_service.dtos.hr.HolidayDTO;
import com.employee_self_service.entities.hr.Holidays;
import com.employee_self_service.utils.CustomDateTimeFormatter;
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
    public abstract Holidays toEntity(HolidayDTO holidayDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract HolidayDTO toDTO(Holidays holidays);

    public abstract List<Holidays> toEntityList(List<HolidayDTO> holidayDTOList);
    public abstract List<HolidayDTO> toDTOList(List<Holidays> holidaysList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(HolidayDTO holidayDTO, @MappingTarget Holidays holidays);

    @AfterMapping
    protected void dateFormatting(Holidays holidays, @MappingTarget HolidayDTO holidayDTO) {
        holidayDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(holidays.getCreatedDate()));
        holidayDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(holidays.getLastUpdatedDate()));
        holidayDTO.setDate(CustomDateTimeFormatter.getLocalDateString(holidays.getDate()));
    }
}
