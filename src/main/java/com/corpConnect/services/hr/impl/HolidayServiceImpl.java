package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.HolidaysDTO;
import com.corpConnect.entities.hr.Holidays;
import com.corpConnect.enumerations.HolidayType;
import com.corpConnect.exceptions.hr.HolidayNotFoundException;
import com.corpConnect.mappers.hr.HolidayMapper;
import com.corpConnect.repositories.hr.HolidayRepository;
import com.corpConnect.services.hr.HolidayService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import com.corpConnect.utils.constants.CorpConnectConstants;
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
    public Holidays getEntity(HolidaysDTO holidaysDTO) {
        return holidayMapper.toEntity(holidaysDTO);
    }

    @Override
    public HolidaysDTO getDTO(Holidays holidays) {
        return holidayMapper.toDTO(holidays);
    }

    @Override
    public List<HolidaysDTO> getDTOList(List<Holidays> holidaysList) {
        return holidayMapper.toDTOList(holidaysList);
    }

    @Override
    public List<Holidays> getEntityList(List<HolidaysDTO> holidaysDTOList) {
        return holidayMapper.toEntityList(holidaysDTOList);
    }

    @Override
    public List<Holidays> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public List<Holidays> getAllHolidaysByMonthAndYear(Integer month, Integer year) {
        return holidayRepository.findByMonthAndYear(month, year);
    }

    private void handleIntegrityViolation(DataIntegrityViolationException e, boolean isWhileCreating, HolidaysDTO holidaysDTO) {
        if (e.getMessage().contains("name")) {
            if(isWhileCreating){
                logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("Holiday", "Name", holidaysDTO.getName(), null));
            } else {
                logger.error(LogConstants.getAlreadyExistsWhileUpdatingMessage("Holiday", "Name", holidaysDTO.getName(), null));
            }
            throw new RuntimeException(CorpConnectConstants.Holiday.HOLIDAY_OF_THE_NAME_EXISTS);
        } else {
            if(isWhileCreating){
                logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("Holiday", "Date", holidaysDTO.getDate(), null));
            } else {
                logger.error(LogConstants.getAlreadyExistsWhileUpdatingMessage("Holiday", "Date", holidaysDTO.getDate(), null));
            }
            throw new RuntimeException(CorpConnectConstants.Holiday.HOLIDAY_FOR_THE_DATE_EXISTS);
        }
    }

    @Override
    public void createHoliday(HolidaysDTO holidaysDTO) {
        try {
            holidayRepository.save(this.getEntity(holidaysDTO));
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e, true, holidaysDTO);
        }
    }

    @Override
    public void updateHoliday(Long holidayId, HolidaysDTO holidaysDTO) {
        Holidays oldHolidays = holidayRepository.findById(holidayId)
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("Holiday", "update", "ID", holidayId, "while updating"));
                    return new HolidayNotFoundException(CorpConnectConstants.Holiday.HOLIDAY_NOT_FOUND);
                });
        try {
            holidayMapper.updateEntityFromDTO(holidaysDTO, oldHolidays);
            holidayRepository.save(oldHolidays);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Holiday", "DTO", holidaysDTO, "ID", holidayId, null));
        } catch (DataIntegrityViolationException e) {
            handleIntegrityViolation(e, false, holidaysDTO);
        }
    }

    @Override
    public void deleteHoliday(HolidaysDTO holidaysDTO, boolean isPermanentDelete) {
        try {
            holidayRepository.delete(this.getEntity(holidaysDTO));
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Holiday", "Permanently", "DTO", holidaysDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Holiday", "DTO", holidaysDTO, null));
            throw new RuntimeException(CorpConnectConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteHolidayById(Long holidayId, boolean isPermanentDelete) {
        try {
            Holidays holiday = holidayRepository.findById(holidayId).orElseThrow(
                    () -> {
                        logger.error(LogConstants.getNotFoundMessage("Holiday", "delete", "ID", holidayId, "while deleting"));
                        return new HolidayNotFoundException(CorpConnectConstants.Holiday.HOLIDAY_NOT_FOUND);
                    }
            );
            if(isPermanentDelete){
                holidayRepository.delete(holiday);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Holiday", "Permanent", "ID", holidayId, null));
            } else {
                holiday.setDeleted(true);
                holidayRepository.save(holiday);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Holiday", "Soft", "ID", holidayId, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Holiday", "ID", holidayId, null));
            throw new RuntimeException(CorpConnectConstants.Holiday.DataIntegrityViolation);
        }
    }

    @Override
    public List<Holidays> getHolidayByHolidayId(Long holidayId) {
        return Collections.singletonList(holidayRepository.findById(holidayId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Holiday", "get", "ID", holidayId, null));
                    return new HolidayNotFoundException(CorpConnectConstants.Holiday.HOLIDAY_NOT_FOUND);
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

