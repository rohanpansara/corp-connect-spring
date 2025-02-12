package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.services.hr.DepartmentService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/department")
@PreAuthorize("hasRole('HR_MANAGER')")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<DepartmentDTO>>> fetchAllDepartments(@RequestParam(required = false, value = "deleted") Boolean isDeleted){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_LIST_FOUND, departmentService.getDTOList(departmentService.getAllDepartments(isDeleted))));
    }

    @GetMapping(value = "/{departmentId}")
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public  ResponseEntity<ResponseDTO<List<DepartmentDTO>>> fetchDepartmentByDepartmentId(@PathVariable("departmentId") Long departmentId){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_FOUND, departmentService.getDTOList(departmentService.getDepartmentById(departmentId))));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr_manager:create')")
    public  ResponseEntity<ResponseDTO<Void>> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_CREATED));
    }

    @PutMapping(value = "/{departmentId}")
    @PreAuthorize("hasAuthority('hr_manager:update')")
    public  ResponseEntity<ResponseDTO<Void>> updateDepartment(@PathVariable(value = "departmentId") Long oldDepartmentId, @RequestBody DepartmentDTO departmentDTO){
        departmentService.updateDepartment(oldDepartmentId,departmentDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_UPDATED));
    }

    @DeleteMapping(value = "/{departmentId}")
    @PreAuthorize("hasAuthority('hr_manager:delete')")
    public  ResponseEntity<ResponseDTO<Void>> softDeleteDepartment(@PathVariable(value = "departmentId") Long departmentId){
        departmentService.deleteDepartmentById(departmentId,false);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_DELETED));
    }

    @DeleteMapping(value = "/{departmentId}/permanent")
    @PreAuthorize("hasAuthority('hr_manager:delete')")
    public  ResponseEntity<ResponseDTO<Void>> permanentDeleteDepartment(@PathVariable(value = "departmentId") Long departmentId){
        departmentService.deleteDepartmentById(departmentId,true);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Department.DEPARTMENT_DELETED));
    }

}
