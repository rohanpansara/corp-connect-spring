package com.corpConnect.controllers.user.userDetails;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.user.userDetails.PunchDetailDTO;
import com.corpConnect.services.user.userDetails.PunchDetailService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/punch-detail")
public class PunchDetailController {

    private final PunchDetailService punchDetailService;

    @PostMapping
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<ResponseDTO<Void>> createPunchDetail(@RequestBody PunchDetailDTO punchDetailDTO) {
        punchDetailService.createPunchDetail(punchDetailDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_CREATED));
    }

    @GetMapping(value = "/{punchDetailId}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<PunchDetailDTO>> fetchPunchDetailById(@PathVariable(value = "punchDetailId") Long punchDetailId) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, punchDetailService.getDTO(punchDetailService.getPunchDetailById(punchDetailId))));
    }

    @GetMapping(value = "/user/{userId}/punches")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<PunchDetailDTO>>> fetchPunchDetailByUserId(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, punchDetailService.getDTOList(punchDetailService.getPunchDetailByUserId(userId))));
    }

    @GetMapping(value = "/department/{departmentId}/punches")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ResponseDTO<List<PunchDetailDTO>>> fetchPunchDetailByDepartmentId(@PathVariable(value = "departmentId") Long departmentId, @RequestParam(value = "date") LocalDate date) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.Record.RECORD_FOUND, punchDetailService.getDTOList(punchDetailService.getPunchDetailByDepartmentIdAndDate(departmentId, date))));
    }

}
