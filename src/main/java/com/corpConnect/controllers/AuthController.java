package com.corpConnect.controllers;

import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.AuthRequestDTO;
import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.security.services.AuthenticationService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/new-user")
    @PreAuthorize("hasAuthority('pms_manager:create') || hasAuthority('hr_manager:create')")
    public ResponseEntity<ResponseDTO<UserDTO>> newUser(@RequestBody NewUserDTO newUserDTO) throws LoginFailedException {
        UserDTO response = authenticationService.addNewUser(newUserDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_CREATED, response));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) throws BaseException{
        AuthResponseDTO response = authenticationService.authenticate(authRequestDTO, "HR");
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.LOGIN_SUCCESS, response));
    }
}
