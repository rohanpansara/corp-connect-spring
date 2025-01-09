package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.LeaveTypeDTO;
import com.corpConnect.entities.hr.LeaveType;
import com.corpConnect.mappers.hr.LeaveTypeMapper;
import com.corpConnect.repositories.hr.LeaveTypeRepository;
import com.corpConnect.services.hr.LeaveTypeService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveTypeServiceImpl.class);

    private final LeaveTypeMapper leaveTypeMapper;
    private final LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveType getEntity(LeaveTypeDTO leaveTypeDTO) {
        return leaveTypeMapper.toEntity(leaveTypeDTO);
    }

    @Override
    public LeaveTypeDTO getDTO(LeaveType leaveType) {
        return leaveTypeMapper.toDTO(leaveType);
    }

    @Override
    public List<LeaveType> getEntityList(List<LeaveTypeDTO> leaveTypeDTOList) {
        return leaveTypeMapper.getEntityList(leaveTypeDTOList);
    }

    @Override
    public List<LeaveTypeDTO> getDTOList(List<LeaveType> leaveTypeList) {
        return leaveTypeMapper.getDTOList(leaveTypeList);
    }

    @Override
    public void createLeaveType(LeaveTypeDTO leaveTypeDTO) {
        try {
            leaveTypeRepository.save(this.getEntity(leaveTypeDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Leave Type", "DTO", leaveTypeDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Leave Type", "Name", leaveTypeDTO.getName(), "while creating"));
            throw new RuntimeException(MessageConstants.LeaveType.LEAVE_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateLeaveType(Long oldLeaveTypeId, LeaveTypeDTO leaveTypeDTO) {
        LeaveType oldLeaveType = leaveTypeRepository.findById(oldLeaveTypeId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Leave type", "update", "ID", oldLeaveTypeId, null));
                    return new RuntimeException(MessageConstants.LeaveType.LEAVE_TYPE_NOT_FOUND);
                });

        try {
            leaveTypeMapper.updateEntityFromDTO(leaveTypeDTO, oldLeaveType);
            leaveTypeRepository.save(oldLeaveType);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Leave type", "DTO", leaveTypeDTO, "ID", oldLeaveTypeId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Leave type", "Name", leaveTypeDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.LeaveType.LEAVE_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteLeaveType(LeaveTypeDTO leaveTypeDTO) {
        LeaveType leaveTypeToDelete = this.getEntity(leaveTypeDTO);

        try {
            leaveTypeRepository.delete(leaveTypeToDelete);
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Leave type", "Permanent", "DTO", leaveTypeDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Leave type", "DTO", leaveTypeDTO, null));
            throw new RuntimeException(MessageConstants.LeaveType.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteLeaveTypeById(Long leaveTypeId) {
        LeaveType leaveTypeToDelete = leaveTypeRepository.findById(leaveTypeId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Leave type", "delete", "ID", leaveTypeId, null));
                    return new RuntimeException(MessageConstants.LeaveType.LEAVE_TYPE_NOT_FOUND);
                });

        try {
            leaveTypeRepository.delete(leaveTypeToDelete);
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Leave type", "Permanent", "ID", leaveTypeId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Leave type", "ID", leaveTypeId, null));
            throw new RuntimeException(MessageConstants.LeaveType.DataIntegrityViolation);
        }
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public Integer getYearlyTotalLeaveCount() {
        return leaveTypeRepository.findTotalMaxLeavesPerYear();
    }
}
