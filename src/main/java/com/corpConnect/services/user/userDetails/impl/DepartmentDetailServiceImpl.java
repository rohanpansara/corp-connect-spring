package com.corpConnect.services.user.userDetails.impl;

import com.corpConnect.dtos.user.userDetails.DepartmentDetailDTO;
import com.corpConnect.entities.user.userDetails.DepartmentDetail;
import com.corpConnect.exceptions.hr.DepartmentRelatedException;
import com.corpConnect.mappers.user.userDetails.DepartmentDetailMapper;
import com.corpConnect.repositories.user.userDetails.DepartmentDetailRepository;
import com.corpConnect.services.user.userDetails.DepartmentDetailService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.MessageCreator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentDetailServiceImpl implements DepartmentDetailService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentDetailServiceImpl.class);

    private final DepartmentDetailMapper departmentDetailMapper;
    private final DepartmentDetailRepository departmentDetailRepository;

    @Override
    public DepartmentDetail getEntity(DepartmentDetailDTO departmentDetailDTO) {
        return departmentDetailMapper.toEntity(departmentDetailDTO);
    }

    @Override
    public DepartmentDetailDTO getDTO(DepartmentDetail departmentDetail) {
        return departmentDetailMapper.toDTO(departmentDetail);
    }

    @Override
    public List<DepartmentDetail> getEntityList(List<DepartmentDetailDTO> departmentDetailDTOList) {
        return departmentDetailMapper.toEntityList(departmentDetailDTOList);
    }

    @Override
    public List<DepartmentDetailDTO> getDTOList(List<DepartmentDetail> departmentDetailList) {
        return departmentDetailMapper.toDTOList(departmentDetailList);
    }

    @Override
    public void createDepartmentDetail(DepartmentDetailDTO departmentDetailDTO) {
        try {
            departmentDetailRepository.save(this.getEntity(departmentDetailDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Department Detail", "DTO", departmentDetailDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Department Detail", "User Id", departmentDetailDTO.getUserDTO().getId(), "User already assigned to the department"));
            throw new RuntimeException(MessageCreator.getDataIntegrityViolationMessage("department detail"));
        } catch (Exception e) {
            logger.error(LogConstants.getUnexpectedErrorMessage("Department Detail", "saving", e.getLocalizedMessage()));
            throw new RuntimeException(MessageCreator.getUnexpectedErrorMessage("saving"));
        }
    }


    @Override
    public void updateDepartmentDetail(Long departmentToUpdateId, DepartmentDetailDTO newDepartmentDetailDTO) {
        DepartmentDetail oldDepartmentDetail = departmentDetailRepository.findById(departmentToUpdateId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Department Detail", "update", "ID", departmentToUpdateId, null));
                    return new DepartmentRelatedException(MessageConstants.Department.DEPARTMENT_NOT_FOUND);
                });

        try {
            departmentDetailMapper.updateEntityFromDTO(newDepartmentDetailDTO, oldDepartmentDetail);
            departmentDetailRepository.save(oldDepartmentDetail);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Department Detail", "DTO", newDepartmentDetailDTO, "ID", departmentToUpdateId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Department Detail", "User Id", newDepartmentDetailDTO.getUserDTO().getId(), "User already assigned to the department"));
            throw new RuntimeException(MessageCreator.getDataIntegrityViolationMessage("department detail"));
        }
    }

    @Override
    public DepartmentDetail getDepartmentDetailById(Long departmentDetailToGetId) {
        return departmentDetailRepository.findById(departmentDetailToGetId).orElseThrow(
                () -> new DepartmentRelatedException(MessageConstants.Department.DEPARTMENT_NOT_FOUND)
        );
    }

    @Override
    public void deleteDepartmentDetail(DepartmentDetailDTO departmentDetailToDeleteDTO) {

    }

    @Override
    public void deleteDepartmentDetailById(Long departmentDetailToDeleteId) {

    }

    @Override
    public List<Long> getUserIdsInTheDepartmentWithId(Long departmentId) {
        return departmentDetailRepository.findUserIdByDepartmentId(departmentId);
    }
}
