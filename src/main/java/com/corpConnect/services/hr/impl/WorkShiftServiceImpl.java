package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.WorkShiftDTO;
import com.corpConnect.entities.hr.WorkShift;
import com.corpConnect.exceptions.hr.HolidayNotFoundException;
import com.corpConnect.exceptions.hr.WorkShiftNotFoundException;
import com.corpConnect.mappers.hr.WorkShiftMapper;
import com.corpConnect.repositories.hr.WorkShiftRepository;
import com.corpConnect.services.hr.WorkShiftService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkShiftServiceImpl implements WorkShiftService {

    private static final Logger logger = LoggerFactory.getLogger(WorkShiftServiceImpl.class);

    private final WorkShiftMapper workShiftMapper;
    private final WorkShiftRepository workShiftRepository;

    @Override
    public WorkShift getEntity(WorkShiftDTO workShiftDTO) {
        return workShiftMapper.toEntity(workShiftDTO);
    }

    @Override
    public WorkShiftDTO getDTO(WorkShift workShift) {
        return workShiftMapper.toDTO(workShift);
    }

    @Override
    public List<WorkShiftDTO> getDTOList(List<WorkShift> workShiftList) {
        return workShiftMapper.toDTOList(workShiftList);
    }

    @Override
    public List<WorkShift> getEntityList(List<WorkShiftDTO> workShiftDTOList) {
        return workShiftMapper.toEntityList(workShiftDTOList);
    }

    @Override
    public void createWorkShift(WorkShiftDTO workShiftDTO) {
        try {
            workShiftRepository.save(this.getEntity(workShiftDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Work Shift", "DTO", workShiftDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Work Shift", "Name", workShiftDTO.getName(), null));
            throw new RuntimeException(MessageConstants.WorkShift.WORK_SHIFT_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateWorkShift(Long oldWorkShiftId, WorkShiftDTO workShiftDTO) {
        WorkShift oldWorkShift = workShiftRepository.findById(oldWorkShiftId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Work Shift", "update", "ID", oldWorkShiftId, null));
                    return new WorkShiftNotFoundException(MessageConstants.WorkShift.WORK_SHIFT_NOT_FOUND);
                });

        try {
            workShiftMapper.updateEntityFromDTO(workShiftDTO, oldWorkShift);
            workShiftRepository.save(oldWorkShift);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Work Shift", "DTO", workShiftDTO, "ID", oldWorkShiftId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Work Shift", "Name", workShiftDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.WorkShift.WORK_SHIFT_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteWorkShift(WorkShiftDTO workShiftDTO) {
        try {
            workShiftRepository.delete(this.getEntity(workShiftDTO));
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Work Shift", "Permanent", "DTO", workShiftDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Work Shift", "DTO", workShiftDTO, null));
            throw new RuntimeException(MessageConstants.WorkShift.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteWorkShiftById(Long workShiftId) {
        WorkShift workShiftToDelete = workShiftRepository.findById(workShiftId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Work Shift", "delete", "ID", workShiftId, null));
                    return new WorkShiftNotFoundException(MessageConstants.WorkShift.WORK_SHIFT_NOT_FOUND);
                });
        try {
            workShiftRepository.delete(workShiftToDelete);
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Work Shift", "Permanent", "ID", workShiftId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Work Shift", "ID", workShiftId, null));
            throw new RuntimeException(MessageConstants.WorkShift.DataIntegrityViolation);
        }
    }

    @Override
    public List<WorkShift> getAllWorkShifts() {
        return workShiftRepository.findAll();
    }

    @Override
    public List<WorkShift> getWorkShiftById(Long workShiftId) {
        return Collections.singletonList(workShiftRepository.findById(workShiftId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Work shift", "get", "ID", workShiftId, null));
                    return new HolidayNotFoundException(MessageConstants.WorkShift.WORK_SHIFT_NOT_FOUND);
                }
        ));
    }
}
