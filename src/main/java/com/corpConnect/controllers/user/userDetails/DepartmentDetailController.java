package com.corpConnect.controllers.user.userDetails;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.user.userDetails.DepartmentDetailDTO;
import com.corpConnect.services.user.userDetails.DepartmentDetailService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/department-detail")
public class DepartmentDetailController {

    private final DepartmentDetailService departmentDetailService;

    @PostMapping
    @PreAuthorize("hasAuthority('pms_manager:create')")
    public ResponseEntity<ResponseDTO<Void>> createDepartmentDetail(@RequestBody DepartmentDetailDTO departmentDetailDTO) {
        departmentDetailService.createDepartmentDetail(departmentDetailDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_CREATED));
    }

    @GetMapping(value = "/{userId}")
    @PreAuthorize("hasAuthority('pms_manager:read') or @corpConnectUserContext.isLoggedUser(#userId)")
    public ResponseEntity<ResponseDTO<List<DepartmentDetailDTO>>> fetchDepartmentDetailByUserId(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, departmentDetailService.getDTOList(departmentDetailService.getDepartmentDetailsByUserId(userId))));
    }

    @GetMapping(value = "/{departmentId}")
    @PreAuthorize("hasAuthority('pms_manager:read')")
    public ResponseEntity<ResponseDTO<List<DepartmentDetailDTO>>> fetchDepartmentDetailByDepartmentId(@PathVariable(value = "departmentId") Long departmentId) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, departmentDetailService.getDTOList(departmentDetailService.getDepartmentDetailsByDepartmentId(departmentId))));
    }

}
