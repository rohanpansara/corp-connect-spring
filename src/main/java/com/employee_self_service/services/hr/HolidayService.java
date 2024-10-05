package com.employee_self_service.services.hr;

import com.employee_self_service.dtos.hr.HolidaysDTO;
import com.employee_self_service.entities.hr.Holidays;

import java.util.List;

public interface HolidayService {

    // Holiday Mapper
    Holidays getEntity(HolidaysDTO holidaysDTO);

    HolidaysDTO getDTO(Holidays holidays);

    List<HolidaysDTO> getDTOList(List<Holidays> holidaysList);

    List<Holidays> getEntityList(List<HolidaysDTO> holidaysDTOList);

    List<Holidays> getAllHolidays();

    List<Holidays> getAllHolidaysByMonthAndYear(Integer month, Integer year);

    void createHoliday(HolidaysDTO holidaysDTO);
    void updateHoliday(Long holidayId, HolidaysDTO holidaysDTO);
    void deleteHoliday(HolidaysDTO holidaysDTO, boolean isPermanentDelete);
    void deleteHolidayById(Long holidayId, boolean isPermanentDelete);

    List<Holidays> getHolidayByHolidayId(Long holidayId);

    List<Holidays> getHolidayByHolidayName(String holidayName);

    List<Holidays> getHolidayByHolidayDate(String holidayDate);

    List<Holidays> getAllHolidaysByHolidayType(String label);
}


