package com.employee_self_service.services.hr;

import com.employee_self_service.dtos.hr.HolidayDTO;
import com.employee_self_service.entities.hr.Holiday;
import com.employee_self_service.enumerations.HolidayType;
import com.employee_self_service.exceptions.hr.HolidayNotFoundException;
import com.employee_self_service.mappers.hr.HolidayMapper;
import com.employee_self_service.repositories.hr.HolidayRepository;
import com.employee_self_service.utils.CustomDateTimeFormatter;
import com.employee_self_service.utils.EssConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    void deleteHoliday(HolidayDTO holidayDTO);
    void deleteHolidayById(Long holidayId);

    List<Holiday> getHolidayByHolidayId(Long holidayId);

    List<Holiday> getHolidayByHolidayName(String holidayName);

    List<Holiday> getHolidayByHolidayDate(String holidayDate);

    List<Holiday> getAllHolidaysByHolidayType(String label);
}


