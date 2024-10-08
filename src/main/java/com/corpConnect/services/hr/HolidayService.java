package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.HolidayDTO;
import com.corpConnect.entities.hr.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {

    // Holiday Mapper
    Holiday getEntity(HolidayDTO holidayDTO);
    HolidayDTO getDTO(Holiday holiday);
    List<HolidayDTO> getDTOList(List<Holiday> holidayList);
    List<Holiday> getEntityList(List<HolidayDTO> holidayDTOList);

    // CRUD Operation
    void createHoliday(HolidayDTO holidayDTO);
    void updateHoliday(Long holidayId, HolidayDTO holidayDTO);
    void deleteHoliday(HolidayDTO holidayDTO);
    void deleteHolidayById(Long holidayId);

    // Holiday Repository
    List<Holiday> getAllHolidays();
    List<Holiday> getHolidayByHolidayId(Long holidayId);
    List<Holiday> getHolidayByHolidayName(String holidayName);
    List<Holiday> getHolidayByHolidayDate(String holidayDate);
    List<Holiday> getAllHolidaysByHolidayType(String label);
    List<Holiday> getAllHolidaysByMonthAndYear(Integer month, Integer year);
    boolean isAHoliday(LocalDate date);
}


