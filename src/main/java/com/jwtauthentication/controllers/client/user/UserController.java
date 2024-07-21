package com.jwtauthentication.controllers.client.user;

import com.jwtauthentication.dtos.client.UserDTO;
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
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> register(@RequestBody RegisterDTO registerDTO) throws LoginFailed, BadRequestException {
        AuthResponseDTO response = authenticationService.addUser(registerDTO);
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.REGISTRATION_SUCCESS, response));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) throws BaseException{
        AuthResponseDTO response = authenticationService.authenticate(authRequestDTO, "HR");
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.LOGIN_SUCCESS, response));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserByUserId(@PathVariable("userId") Long userId) throws BaseException{
        return ResponseEntity.ok(ResponseDTO.success(EssConstants.UserSuccess.USER_FOUND, userService.getDTO(userService.getUserByUserId(userId))));
    }
}
