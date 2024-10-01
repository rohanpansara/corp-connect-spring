package com.employee_self_service.controllers.hr;

import com.employee_self_service.dtos.common.ResponseDTO;
import com.employee_self_service.services.users.UserService;
import com.employee_self_service.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/access-control")
@PreAuthorize("hasRole('HR_ADMIN')")
public class AccessController {

    private final UserService userService;

    @PutMapping("/unlock/{user-id}")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> unlockAccountByUserId(@PathVariable("user-id") Long userId){
        userService.unlockUserAccount(userId);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.HrAccessControl.USER_UNLOCKED));
    }

    @PutMapping("/enable/{user-id}")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> enableAccountByUserId(@PathVariable("user-id") Long userId){
        userService.enableUserAccount(userId);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.HrAccessControl.USER_ENABLED));
    }

    @PutMapping("/lock/{user-id}")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> lockAccountByUserId(@PathVariable("user-id") Long userId){
        userService.lockUserAccount(userId);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.HrAccessControl.USER_LOCKED));
    }

    @PutMapping("/disable/{user-id}")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> disableAccountByUserId(@PathVariable("user-id") Long userId){
        userService.disableUserAccount(userId);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.HrAccessControl.USER_DISABLED));
    }
}
