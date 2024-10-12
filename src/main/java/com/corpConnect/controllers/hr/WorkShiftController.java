package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.hr.WorkShiftDTO;
import com.corpConnect.services.hr.WorkShiftService;
import com.corpConnect.utils.constants.MessageConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/work-shift")
@PreAuthorize("hasRole('HR_MANAGER')")
public class WorkShiftController {

    private final WorkShiftService workShiftService;

    @GetMapping
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<WorkShiftDTO>>> fetchAllJobTitles() {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.WorkShift.WORK_SHIFT_LIST_FOUND, workShiftService.getDTOList(workShiftService.getAllWorkShifts())));
    }

    @GetMapping(value = "/{work-shift-id}")
    @PreAuthorize("hasAuthority('hr_manager:read')")
    public ResponseEntity<ResponseDTO<List<WorkShiftDTO>>> fetchWorkShiftByWorkShiftId(@PathVariable("work-shift-id") Long workShiftId) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.WorkShift.WORK_SHIFT_FOUND, workShiftService.getDTOList(workShiftService.getWorkShiftById(workShiftId))));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr_admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createWorkShift(@Valid @RequestBody WorkShiftDTO workShiftDTO) {
        workShiftService.createWorkShift(workShiftDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.WorkShift.WORK_SHIFT_CREATED));
    }

    @PutMapping(value = "/{work-shift-id}")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<Void>> updateWorkShift(@PathVariable("work-shift-id") Long oldWorkShiftId, @Valid @RequestBody WorkShiftDTO newWorkShiftDTO) {
        workShiftService.updateWorkShift(oldWorkShiftId, newWorkShiftDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.WorkShift.WORK_SHIFT_UPDATED));
    }

    @DeleteMapping(value = "/{work-shift-id}")
    @PreAuthorize("hasAuthority('hr_admin:delete')")
    public ResponseEntity<ResponseDTO<Void>> softDeleteWorkShift(@PathVariable("work-shift-id") Long workShiftId) {
        workShiftService.deleteWorkShiftById(workShiftId);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.WorkShift.WORK_SHIFT_DELETED));
    }

}
