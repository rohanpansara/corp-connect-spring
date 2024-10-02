package com.employee_self_service.services.hr;

import com.employee_self_service.dtos.hr.HolidayDTO;
import com.employee_self_service.entities.hr.Holidays;

import java.util.List;

public interface HolidayService {

    // Holiday Mapper
    Holidays getEntity(HolidayDTO holidayDTO);

    HolidayDTO getDTO(Holidays holidays);

    List<HolidayDTO> getDTOList(List<Holidays> holidaysList);

    List<Holidays> getEntityList(List<HolidayDTO> holidayDTOList);

    List<Holidays> getAllHolidays();

    List<Holidays> getAllHolidaysByMonthAndYear(Integer month, Integer year);

    void createHoliday(HolidayDTO holidayDTO);
    void updateHoliday(Long holidayId, HolidayDTO holidayDTO);
    void deleteHoliday(HolidayDTO holidayDTO);
    void deleteHolidayById(Long holidayId);

    List<Holidays> getHolidayByHolidayId(Long holidayId);

    List<Holidays> getHolidayByHolidayName(String holidayName);

    List<Holidays> getHolidayByHolidayDate(String holidayDate);

    List<Holidays> getAllHolidaysByHolidayType(String label);
}


