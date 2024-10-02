package com.employee_self_service.services.hr.impl;

import com.employee_self_service.dtos.hr.HolidayDTO;
import com.employee_self_service.entities.hr.Holidays;
import com.employee_self_service.enumerations.HolidayType;
import com.employee_self_service.exceptions.hr.HolidayNotFoundException;
import com.employee_self_service.mappers.hr.HolidayMapper;
import com.employee_self_service.repositories.hr.HolidayRepository;
import com.employee_self_service.services.hr.HolidayService;
import com.employee_self_service.utils.CustomDateTimeFormatter;
import com.employee_self_service.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

    private final HolidayMapper holidayMapper;
    private final HolidayRepository holidayRepository;

    @Override
    public Holidays getEntity(HolidayDTO holidayDTO) {
        return holidayMapper.toEntity(holidayDTO);
    }

    @Override
    public HolidayDTO getDTO(Holidays holidays) {
        return holidayMapper.toDTO(holidays);
    }

    @Override
    public List<HolidayDTO> getDTOList(List<Holidays> holidaysList) {
        return holidayMapper.toDTOList(holidaysList);
    }

    @Override
    public List<Holidays> getEntityList(List<HolidayDTO> holidayDTOList) {
        return holidayMapper.toEntityList(holidayDTOList);
    }

    @Override
    public List<Holidays> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public List<Holidays> getAllHolidaysByMonthAndYear(Integer month, Integer year) {
        return holidayRepository.findByMonthAndYear(month, year);
    }

    private void handleIntegrityViolation(DataIntegrityViolationException e) {
        if (e.getMessage().contains("name")) {
            logger.error("Already Exists: Attempt to create or update a holiday [duplicate name]");
            throw new RuntimeException(EssConstants.Holiday.HOLIDAY_OF_THE_NAME_EXISTS);
        } else {
            logger.error("Already Exists: Attempt to create or update a holiday [duplicate date]");
            throw new RuntimeException(EssConstants.Holiday.HOLIDAY_FOR_THE_DATE_EXISTS);
        }
    }

    @Override
    public void createHoliday(HolidayDTO holidayDTO) {
        try {
            holidayRepository.save(this.getEntity(holidayDTO));
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e);
        }
    }

    @Override
    public void updateHoliday(Long holidayId, HolidayDTO holidayDTO) {
        Holidays oldHolidays = holidayRepository.findById(holidayId)
                .orElseThrow(() -> {
                    logger.error("Not Found: Attempt to update a holiday with id: {}", holidayId);
                    return new HolidayNotFoundException(EssConstants.Holiday.HOLIDAY_NOT_FOUND);
                });
        try {
            holidayMapper.updateEntityFromDTO(holidayDTO, oldHolidays);
            holidayRepository.save(oldHolidays);
            logger.info("Updated: Attempt to update a holiday with dto: {}", holidayDTO);
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e);
        }
    }

    @Override
    public void deleteHoliday(HolidayDTO holidayDTO) {
        try {
            holidayRepository.delete(this.getEntity(holidayDTO));
            logger.info("Permanent Delete: Attempt to delete a holiday with dto: {}", holidayDTO);
        } catch (DataIntegrityViolationException e) {
            logger.error("In Use: Attempt to delete a holiday with dto: {}", holidayDTO);
            throw new RuntimeException(EssConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteHolidayById(Long holidayId) {
        try {
            holidayRepository.deleteById(holidayId);
            logger.error("Permanent Delete: Attempt to delete a holiday with id: {}", holidayId);
        } catch (DataIntegrityViolationException e) {
            logger.error("In Use: Attempt to delete a holiday with id: {}", holidayId);
            throw new RuntimeException(EssConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public List<Holidays> getHolidayByHolidayId(Long holidayId) {
        return Collections.singletonList(holidayRepository.findById(holidayId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to get holiday with id: {}", holidayId);
                    return new HolidayNotFoundException(EssConstants.Holiday.HOLIDAY_NOT_FOUND);
                }
        ));
    }

    @Override
    public List<Holidays> getHolidayByHolidayName(String holidayName) {
        return holidayRepository.findByNameContainingIgnoreCase(holidayName);
    }

    @Override
    public List<Holidays> getHolidayByHolidayDate(String holidayDate) {
        return Collections.singletonList(holidayRepository.findByDate(CustomDateTimeFormatter.getLocalDateObject(holidayDate)));
    }

    @Override
    public List<Holidays> getAllHolidaysByHolidayType(String label) {
        HolidayType type = HolidayType.getByLabel(label);
        return holidayRepository.findByType(type);
    }
}

