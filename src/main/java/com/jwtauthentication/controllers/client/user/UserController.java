package com.jwtauthentication.controllers.client.user;

import com.jwtauthentication.security.dtos.AuthenticationRequest;
import com.jwtauthentication.security.dtos.AuthenticationResponse;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.security.services.AuthenticationService;
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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authenticationService.addUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest,"HR"));
    }
}
