package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.HolidayDTO;
import com.corpConnect.entities.hr.Holiday;
import com.corpConnect.enumerations.HolidayType;
import com.corpConnect.exceptions.hr.HolidayRelatedException;
import com.corpConnect.mappers.hr.HolidayMapper;
import com.corpConnect.repositories.hr.HolidayRepository;
import com.corpConnect.services.hr.HolidayService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

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

    @Override
    public boolean isAHoliday(LocalDate date) {
        return holidayRepository.existsByDate(date);
    }

    private void handleIntegrityViolation(DataIntegrityViolationException e, boolean isWhileCreating, HolidayDTO holidayDTO) {
        if (e.getMessage().contains("hr_holidays.name")) {
            if (isWhileCreating) {
                logger.error(LogConstants.getAlreadyExistsMessage("Holiday", "Name", holidayDTO.getName(), "while creating"));
            } else {
                logger.error(LogConstants.getAlreadyExistsMessage("Holiday", "Name", holidayDTO.getName(), "while updating"));
            }
            throw new RuntimeException(MessageConstants.Holiday.HOLIDAY_OF_THE_NAME_EXISTS);
        } else {
            if (isWhileCreating) {
                logger.error(LogConstants.getAlreadyExistsMessage("Holiday", "Date", holidayDTO.getDate(), "while creating"));
            } else {
                logger.error(LogConstants.getAlreadyExistsMessage("Holiday", "Date", holidayDTO.getDate(), "while updating"));
            }
            throw new RuntimeException(MessageConstants.Holiday.HOLIDAY_FOR_THE_DATE_EXISTS);
        }
    }

    @Override
    public void createHoliday(HolidayDTO holidayDTO) {
        try {
            holidayRepository.save(this.getEntity(holidayDTO));
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e, true, holidayDTO);
        }
    }

    @Override
    public void updateHoliday(Long holidayId, HolidayDTO holidayDTO) {
        Holiday oldHoliday = holidayRepository.findById(holidayId)
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("Holiday", "update", "ID", holidayId, "while updating"));
                    return new HolidayRelatedException(MessageConstants.Holiday.HOLIDAY_NOT_FOUND);
                });
        try {
            holidayMapper.updateEntityFromDTO(holidayDTO, oldHoliday);
            holidayRepository.save(oldHoliday);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Holiday", "DTO", holidayDTO, "ID", holidayId, null));
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e, false, holidayDTO);
        }
    }

    @Override
    public void deleteHoliday(HolidayDTO holidayDTO) {
        try {
            holidayRepository.delete(this.getEntity(holidayDTO));
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Holiday", "Permanently", "DTO", holidayDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Holiday", "DTO", holidayDTO, null));
            throw new RuntimeException(MessageConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteHolidayById(Long holidayId) {
        try {
            Holiday holiday = holidayRepository.findById(holidayId).orElseThrow(
                    () -> {
                        logger.error(LogConstants.getNotFoundMessage("Holiday", "delete", "ID", holidayId, "while deleting"));
                        return new HolidayRelatedException(MessageConstants.Holiday.HOLIDAY_NOT_FOUND);
                    }
            );
            holidayRepository.delete(holiday);
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Holiday", "Permanent", "ID", holidayId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Holiday", "ID", holidayId, null));
            throw new RuntimeException(MessageConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public List<Holiday> getHolidayByHolidayId(Long holidayId) {
        return Collections.singletonList(holidayRepository.findById(holidayId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Holiday", "get", "ID", holidayId, null));
                    return new HolidayRelatedException(MessageConstants.Holiday.HOLIDAY_NOT_FOUND);
                }
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

