package com.employee_self_service.services.hr.impl;

import com.employee_self_service.dtos.hr.HolidayDTO;
import com.employee_self_service.entities.hr.Holiday;
import com.employee_self_service.enumerations.HolidayType;
import com.employee_self_service.exceptions.hr.HolidayNotFoundException;
import com.employee_self_service.mappers.hr.HolidayMapper;
import com.employee_self_service.repositories.hr.HolidayRepository;
import com.employee_self_service.services.hr.HolidayService;
import com.employee_self_service.utils.CustomDateTimeFormatter;
import com.employee_self_service.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayMapper holidayMapper;
    private final HolidayRepository holidayRepository;

    @Override
    public Holiday getEntity(HolidayDTO holidayDTO) {
        return holidayMapper.toEntity(holidayDTO);
    }

    @Override
    public HolidayDTO getDTO(Holiday holiday) {
        return holidayMapper.toDTO(holiday);
    }

    @Override
    public List<HolidayDTO> getDTOList(List<Holiday> holidayList) {
        return holidayMapper.toDTOList(holidayList);
    }

    @Override
    public List<Holiday> getEntityList(List<HolidayDTO> holidayDTOList) {
        return holidayMapper.toEntityList(holidayDTOList);
    }

    @Override
    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public List<Holiday> getAllHolidaysByMonthAndYear(Integer month, Integer year) {
        return holidayRepository.findByMonthAndYear(month, year);
    }

    private void validateHoliday(HolidayDTO holidayDTO) {
        if (holidayRepository.findByName(holidayDTO.getName()) != null) {
            throw new RuntimeException(EssConstants.Holiday.HOLIDAY_OF_THE_NAME_EXISTS);
        }

        if (holidayRepository.findByDate(CustomDateTimeFormatter.getLocalDateObject(holidayDTO.getDate())) != null) {
            throw new RuntimeException(EssConstants.Holiday.HOLIDAY_FOR_THE_DATE_EXISTS);
        }
    }

    private void handleIntegrityViolation(DataIntegrityViolationException e) {
        if (e.getMessage().contains("name")) {
            throw new RuntimeException(EssConstants.Holiday.HOLIDAY_OF_THE_NAME_EXISTS);
        } else {
            throw new RuntimeException(EssConstants.Holiday.HOLIDAY_FOR_THE_DATE_EXISTS);
        }
    }

    @Override
    public void createHoliday(HolidayDTO holidayDTO) {
        validateHoliday(holidayDTO);
        try {
            holidayRepository.save(this.getEntity(holidayDTO));
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e);
        }
    }

    @Override
    public void updateHoliday(Long holidayId, HolidayDTO holidayDTO) {
        Holiday oldHoliday = holidayRepository.findById(holidayId)
                .orElseThrow(() -> new HolidayNotFoundException(EssConstants.Holiday.HOLIDAY_NOT_FOUND));

        try {
            holidayMapper.updateEntityFromDTO(holidayDTO, oldHoliday);
            holidayRepository.save(oldHoliday);
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e);
        }
    }

    @Override
    public void deleteHoliday(HolidayDTO holidayDTO) {
        try {
            holidayRepository.delete(this.getEntity(holidayDTO));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(EssConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteHolidayById(Long holidayId) {
        try {
            holidayRepository.deleteById(holidayId);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(EssConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public List<Holiday> getHolidayByHolidayId(Long holidayId) {
        return Collections.singletonList(holidayRepository.findById(holidayId).orElseThrow(
                () -> new HolidayNotFoundException(EssConstants.Holiday.HOLIDAY_NOT_FOUND)
        ));
    }

    @Override
    public List<Holiday> getHolidayByHolidayName(String holidayName) {
        return holidayRepository.findByNameContainingIgnoreCase(holidayName);
    }

    @Override
    public List<Holiday> getHolidayByHolidayDate(String holidayDate) {
        return Collections.singletonList(holidayRepository.findByDate(CustomDateTimeFormatter.getLocalDateObject(holidayDate)));
    }

    @Override
    public List<Holiday> getAllHolidaysByHolidayType(String label) {
        HolidayType type = HolidayType.getByLabel(label);
        return holidayRepository.findByType(type);
    }
}

