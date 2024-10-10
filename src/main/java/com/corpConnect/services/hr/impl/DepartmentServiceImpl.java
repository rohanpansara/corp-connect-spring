package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.entities.hr.Department;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.exceptions.hr.JobTitleNotFoundException;
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
                    return new JobTitleNotFoundException(MessageConstants.Department.DEPARTMENT_NOT_FOUND);
                });

        try {
            departmentMapper.updateEntityFromDTO(departmentDTO, oldDepartment);
            departmentRepository.save(oldDepartment);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Department", "DTO", departmentDTO, "ID", oldDepartmentId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Department", "Name", departmentDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.JobTitle.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteDepartment(DepartmentDTO departmentDTO) {

    }

    @Override
    public void deleteDepartmentById(Long departmentId, Boolean isPermanentDelete) {

    }

    @Override
    public List<Department> getAllDepartments() {
        return List.of();
    }

    @Override
    public List<Department> getAllNonDeletedDepartments() {
        return List.of();
    }

    @Override
    public List<Department> getAllDeletedDepartments() {
        return List.of();
    }

    @Override
    public List<Department> getDepartmentById(Long jobTitlesId) {
        return List.of();
    }

    @Override
    public List<Department> getDepartmentByName(String jobTitlesName) {
        return List.of();
    }

    @Override
    public List<Department> getDepartmentByGrade(String grade) {
        return List.of();
    }
}
