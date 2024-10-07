package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.HolidayDTO;
import com.corpConnect.entities.hr.Holiday;

import java.util.List;

public interface HolidayService {

    // Holiday Mapper
    Holiday getEntity(HolidayDTO holidayDTO);

    HolidayDTO getDTO(Holiday holiday);

    List<HolidayDTO> getDTOList(List<Holiday> holidayList);

    List<Holiday> getEntityList(List<HolidayDTO> holidayDTOList);

    List<Holiday> getAllHolidays();

    List<Holiday> getAllHolidaysByMonthAndYear(Integer month, Integer year);

    void createHoliday(HolidayDTO holidayDTO);
    void updateHoliday(Long holidayId, HolidayDTO holidayDTO);
    void deleteHoliday(HolidayDTO holidayDTO, boolean isPermanentDelete);
    void deleteHolidayById(Long holidayId, boolean isPermanentDelete);

    List<Holiday> getHolidayByHolidayId(Long holidayId);

    List<Holiday> getHolidayByHolidayName(String holidayName);

    List<Holiday> getHolidayByHolidayDate(String holidayDate);

    List<Holiday> getAllHolidaysByHolidayType(String label);
}


