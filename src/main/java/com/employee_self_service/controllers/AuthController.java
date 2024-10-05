package com.employee_self_service.controllers;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.common.ResponseDTO;
import com.employee_self_service.exceptions.client.LoginFailedException;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.security.dtos.AuthRequestDTO;
import com.employee_self_service.security.dtos.AuthResponseDTO;
import com.employee_self_service.security.dtos.RegisterDTO;
import com.employee_self_service.security.services.AuthenticationService;
import com.employee_self_service.utils.constants.EssConstants;
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

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('pms_manager:create') || hasAuthority('hr_manager:create')")
    public ResponseEntity<ResponseDTO<UserDTO>> register(@RequestBody RegisterDTO registerDTO) throws LoginFailedException {
        UserDTO response = authenticationService.addUser(registerDTO);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.REGISTRATION_SUCCESS, response));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) throws BaseException{
        AuthResponseDTO response = authenticationService.authenticate(authRequestDTO, "HR");
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.LOGIN_SUCCESS, response));
    }
}
