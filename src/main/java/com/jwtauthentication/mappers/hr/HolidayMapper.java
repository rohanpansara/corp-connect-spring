package com.jwtauthentication.mappers.hr;

import com.jwtauthentication.dtos.hr.HolidayDTO;
import com.jwtauthentication.entities.hr.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class HolidayMapper {

    @Mapping(source = "holiday", target = "name")
    public abstract Holiday toEntity(HolidayDTO holidayDTO);

    @Mapping(source = "name", target = "holiday")
    public abstract HolidayDTO toDTO(Holiday holiday);
}
