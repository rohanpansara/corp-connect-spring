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

    List<JobTitle> getAllDepartments();
    List<JobTitle> getAllNonDeletedDepartments();
    List<JobTitle> getAllDeletedDepartments();
    List<JobTitle> getDepartmentById(Long jobTitlesId);
    List<JobTitle> getDepartmentByName(String jobTitlesName);
    List<JobTitle> getDepartmentByGrade(String grade);

}
