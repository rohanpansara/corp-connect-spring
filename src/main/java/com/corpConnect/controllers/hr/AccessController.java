package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PatchMapping("/{user-id}/account-enable")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> enableAccountByUserId(@PathVariable("user-id") Long userId, @RequestParam(name = "toBeDisabled", required = false) Boolean toBeDisabled){
        if(Boolean.TRUE.equals(toBeDisabled)) {
            userService.disableUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_DISABLED));
        } else {
            userService.enableUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_ENABLED));
        }
    }

    @PatchMapping("/{user-id}/account-unlock")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> unlockAccountByUserId(@PathVariable("user-id") Long userId, @RequestParam(name = "toBeLocked", required = false) Boolean toBeLocked){
        if(Boolean.TRUE.equals(toBeLocked)) {
            userService.lockUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_LOCKED));
        } else {
            userService.unlockUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_UNLOCKED));
        }
    }
}
