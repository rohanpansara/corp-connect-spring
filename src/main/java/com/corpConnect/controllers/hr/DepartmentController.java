package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.services.hr.DepartmentService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/hr/department")
@PreAuthorize("hasRole('HR_ADMIN')")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<DepartmentDTO>>> fetchAllDepartments(){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_LIST_FOUND, departmentService.getDTOList(departmentService.getAllDepartments())));
    }

    @GetMapping(value = "/{department-id}")
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public  ResponseEntity<ResponseDTO<List<DepartmentDTO>>> fetchDepartmentByDepartmentId(@PathVariable("department-id") Long departmentId){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_FOUND, departmentService.getDTOList(departmentService.getDepartmentById(departmentId))));
    }
}
