package com.corpConnect.controllers;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.enumerations.OTPType;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.security.dtos.PasswordDTO;
import com.corpConnect.security.services.AuthenticationService;
import com.corpConnect.services.company.OTPService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {

    private final AuthenticationService authenticationService;
    private final OTPService otpService;

    // TODO: remove this once the user table is final
    @PostMapping(value = "/new-user")
    public ResponseEntity<ResponseDTO<UserDTO>> addNewUser(@RequestBody NewUserDTO newUserDTO) throws LoginFailedException {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_CREATED, authenticationService.verifyNewUser(newUserDTO)));
    }

    @PutMapping(value = "/set-password")
    public ResponseEntity<ResponseDTO<Void>> setPassword(@RequestBody PasswordDTO passwordDTO) {
        authenticationService.createUserPassword(passwordDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.PASSWORD_SET_SUCCESSFULLY));
    }

    @GetMapping("/validate/new-user/for-email-pending-otp")
    public ResponseEntity<ResponseDTO<Boolean>> validatePendingOTP(@RequestParam("userId") Long userId) {
        if (otpService.validateForPendingOTPVerification(userId, OTPType.NEW_USER_VERIFICATION)) {
            return ResponseEntity.badRequest().body(ResponseDTO.error(MessageConstants.OTP.PENDING_OTP_NOT_FOUND, true));
        }
        return ResponseEntity.badRequest().body(ResponseDTO.error(MessageConstants.OTP.PENDING_OTP_FOUND, false));
    }

    @PutMapping(value = "/validate/new-user/email-otp")
    public ResponseEntity<ResponseDTO<Void>> verifyOTP(@RequestParam("userId") Long userId, @RequestParam("otp") String otp) {
        otpService.verifyNewUserOTP(userId, otp);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.EmailSuccess.EMAIL_VERIFIED));
    }

}
