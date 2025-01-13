package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.entities.hr.Department;
import com.corpConnect.exceptions.hr.DepartmentRelatedException;
import com.corpConnect.exceptions.hr.HolidayRelatedException;
import com.corpConnect.mappers.hr.DepartmentMapper;
import com.corpConnect.repositories.hr.DepartmentRepository;
import com.corpConnect.services.hr.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public Department getEntity(DepartmentDTO departmentDTO) {
        return departmentMapper.toEntity(departmentDTO);
    }

    @Override
    public DepartmentDTO getDTO(Department department) {
        return departmentMapper.toDTO(department);
    }

    @Override
    public List<Department> getEntityList(List<DepartmentDTO> departmentDTOList) {
        return departmentMapper.toEntityList(departmentDTOList);
    }

    @Override
    public List<DepartmentDTO> getDTOList(List<Department> departmentList) {
        return departmentMapper.toDTOList(departmentList);
    }

    @Override
    public void createDepartment(DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(this.getEntity(departmentDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Department", "DTO", departmentDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Department", "Name", departmentDTO.getName(), "while creating"));
            throw new RuntimeException(MessageConstants.Department.DEPARTMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateDepartment(Long oldDepartmentId, DepartmentDTO departmentDTO) {
        Department oldDepartment = departmentRepository.findById(oldDepartmentId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Department", "update", "ID", oldDepartmentId, null));
                    return new DepartmentRelatedException(MessageConstants.Department.DEPARTMENT_NOT_FOUND);
                });

        try {
            departmentMapper.updateEntityFromDTO(departmentDTO, oldDepartment);
            departmentRepository.save(oldDepartment);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Department", "DTO", departmentDTO, "ID", oldDepartmentId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Department", "Name", departmentDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.Department.DEPARTMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteDepartment(DepartmentDTO departmentDTO, Boolean isPermanentDelete) {
        Department departmentToDelete = this.getEntity(departmentDTO);
        try {
            if(isPermanentDelete){
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Department", "Permanent", "DTO", departmentDTO, null));
                departmentRepository.delete(departmentToDelete);
            } else {
                departmentToDelete.setDeleted(true);
                departmentRepository.save(departmentToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Department", "Soft", "DTO", departmentDTO, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Department", "DTO", departmentDTO, null));
            throw new RuntimeException(MessageConstants.Department.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteDepartmentById(Long departmentId, Boolean isPermanentDelete) {
        Department departmentToDelete = departmentRepository.findById(departmentId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Department", "delete", "ID", departmentId, null));
                    return new DepartmentRelatedException(MessageConstants.Department.DEPARTMENT_NOT_FOUND);
                });

        try {
            if(isPermanentDelete){
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Department", "Permanent", "ID", departmentId, null));
                departmentRepository.delete(departmentToDelete);
            } else {
                departmentToDelete.setDeleted(true);
                departmentRepository.save(departmentToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Department", "Soft", "ID", departmentId, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Department", "ID", departmentId, null));
            throw new RuntimeException(MessageConstants.Department.DataIntegrityViolation);
        }
    }

    @Override
    public List<Department> getAllDepartments(Boolean isDeleted) {
        if(isDeleted == null){
            logger.info(LogConstants.getFoundAllMessage("Department", "get", "without deleted check"));
            return departmentRepository.findAll();
        } else if(isDeleted){
            return this.getAllDeletedDepartments();
        } else {
            return this.getAllNonDeletedDepartments();
        }
    }

    @Override
    public List<Department> getAllNonDeletedDepartments() {
        logger.info(LogConstants.getFoundAllMessage("Department", "get", "deleted check-"+ true));
        return departmentRepository.findByDeleted(false);
    }

    @Override
    public List<Department> getAllDeletedDepartments() {
        logger.info(LogConstants.getFoundAllMessage("Department", "get", "deleted check-"+ false));
        return departmentRepository.findByDeleted(true);
    }

    @Override
    public List<Department> getDepartmentById(Long departmentId) {
        return Collections.singletonList(departmentRepository.findById(departmentId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Department", "get", "ID", departmentId, null));
                    return new HolidayRelatedException(MessageConstants.Department.DEPARTMENT_NOT_FOUND);
                }
        ));
    }

    @Override
    public List<Department> getDepartmentByName(String departmentName) {
        logger.info(LogConstants.getFoundAllMessage("Department", "get", "name containing-"+ departmentName));
        return departmentRepository.findByNameContaining(departmentName);
    }

}
