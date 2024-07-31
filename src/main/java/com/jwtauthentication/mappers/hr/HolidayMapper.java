package com.jwtauthentication.mappers.hr;

import com.jwtauthentication.dtos.hr.HolidayDTO;
import com.jwtauthentication.entities.hr.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
}
