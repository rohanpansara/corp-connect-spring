package com.jwtauthentication.controllers.hr;

import com.jwtauthentication.dtos.common.ResponseDTO;
import com.jwtauthentication.services.UserService;
import com.jwtauthentication.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/access-control")
@PreAuthorize("hasRole('HR_ADMIN')")
public class HRAccessController {
    private final UserService userService;

    @PutMapping("/unlock")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> unlockUserByUserId(@RequestParam("userId") Long userId){
        userService.unlockUserAccount(userId);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.HrAccessControl.USER_UNLOCKED));
    }

    @PutMapping("/enable")
    @PreAuthorize("hasAuthority('hr_admin:update')")
    public ResponseEntity<ResponseDTO<String>> enableUserByUserId(@RequestParam("userId") Long userId){
        userService.enableUserAccount(userId);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.HrAccessControl.USER_ENABLED));
    }
}
