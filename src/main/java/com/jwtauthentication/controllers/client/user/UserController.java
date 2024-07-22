package com.jwtauthentication.controllers.client.user;

import com.jwtauthentication.dtos.common.ResponseDTO;
import com.jwtauthentication.exceptions.client.LoginFailed;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.security.dtos.AuthRequestDTO;
import com.jwtauthentication.security.dtos.AuthResponseDTO;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.security.services.AuthenticationService;
import com.jwtauthentication.services.UserService;
import com.jwtauthentication.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> register(@RequestBody RegisterDTO registerDTO) throws LoginFailed {
        AuthResponseDTO response = authenticationService.addUser(registerDTO);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.REGISTRATION_SUCCESS, response));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) throws BaseException{
        AuthResponseDTO response = authenticationService.authenticate(authRequestDTO, "HR");
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.LOGIN_SUCCESS, response));
    }
}
