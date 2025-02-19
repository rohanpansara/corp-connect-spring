package com.corpConnect.controllers.hr;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.security.services.AuthenticationService;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr/user-access-control")
@PreAuthorize("hasRole('HR_MANAGER')")
public class UserAccessController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/new-user")
    @PreAuthorize("hasAuthority('hr_manager:create') or hasAuthority('pms_manager:create')")
    public ResponseEntity<ResponseDTO<UserDTO>> addNewUser(@RequestBody NewUserDTO newUserDTO) throws LoginFailedException {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_CREATED, authenticationService.verifyNewUser(newUserDTO)));
    }

    @PatchMapping("/{userId}/enable-account")
    @PreAuthorize("hasAuthority('hr_manager:update')")
    public ResponseEntity<ResponseDTO<String>> enableAccountByUserId(@PathVariable("userId") Long userId, @RequestParam(name = "toBeDisabled", required = false) Boolean toBeDisabled){
        if(Boolean.TRUE.equals(toBeDisabled)) {
            userService.disableUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_DISABLED));
        } else {
            userService.enableUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_ENABLED));
        }
    }

    @PatchMapping("/{userId}/unlock-account")
    @PreAuthorize("hasAuthority('hr_manager:update')")
    public ResponseEntity<ResponseDTO<String>> unlockAccountByUserId(@PathVariable("userId") Long userId, @RequestParam(name = "toBeLocked", required = false) Boolean toBeLocked){
        if(Boolean.TRUE.equals(toBeLocked)) {
            userService.lockUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_LOCKED));
        } else {
            userService.unlockUserAccount(userId);
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.HrAccessControl.USER_UNLOCKED));
        }
    }
}
