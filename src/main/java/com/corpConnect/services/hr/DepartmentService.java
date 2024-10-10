package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.entities.hr.Department;
import com.corpConnect.entities.hr.JobTitle;

import java.util.List;

public interface DepartmentService {

    Department getEntity(DepartmentDTO departmentDTO);
    DepartmentDTO getDTO(Department department);
    List<Department> getEntityList(List<DepartmentDTO> departmentDTOList);
    List<DepartmentDTO> getDTOList(List<Department> departmentList);

    void createDepartment(DepartmentDTO departmentDTO);
    void updateDepartment(Long oldDepartmentId, DepartmentDTO departmentDTO);
    void deleteDepartment(DepartmentDTO departmentDTO);
    void deleteDepartmentById(Long departmentId, Boolean isPermanentDelete);

    List<Department> getAllDepartments();
    List<Department> getAllNonDeletedDepartments();
    List<Department> getAllDeletedDepartments();
    List<Department> getDepartmentById(Long jobTitlesId);
    List<Department> getDepartmentByName(String jobTitlesName);
    List<Department> getDepartmentByGrade(String grade);

}
