package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.entities.hr.Department;

import java.util.List;

public interface DepartmentService {

    Department getEntity(DepartmentDTO departmentDTO);
    DepartmentDTO getDTO(Department department);
    List<Department> getEntityList(List<DepartmentDTO> departmentDTOList);
    List<DepartmentDTO> getDTOList(List<Department> departmentList);

    void createDepartment(DepartmentDTO departmentDTO);
    void updateDepartment(Long oldDepartmentId, DepartmentDTO departmentDTO);
    void deleteDepartment(DepartmentDTO departmentDTO, Boolean isPermanentDelete);
    void deleteDepartmentById(Long departmentId, Boolean isPermanentDelete);

    List<Department> getAllDepartments(Boolean isDeleted);
    List<Department> getAllNonDeletedDepartments();
    List<Department> getAllDeletedDepartments();
    List<Department> getDepartmentById(Long departmentId);
    List<Department> getDepartmentByName(String departmentName);

}
