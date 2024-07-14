package com.jwtauthentication.security.services;

import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.repositories.UserRepository;
import com.jwtauthentication.security.dtos.AuthenticationRequest;
import com.jwtauthentication.security.dtos.AuthenticationResponse;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse addUser(RegisterDTO registerDTO) {

        var user = userService.getEntityFromRegisterDTO(registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        user.setLastUpdatedDate(LocalDateTime.now());

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateTokenForUser(user, savedUser.getName(), "HR");
        var refreshToken = jwtService.generateRefreshTokenForUser(user, savedUser.getName(), "HR");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userService.getDTO(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request, String moduleType) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            var jwtToken = jwtService.generateTokenForUser(user, user.getName(), moduleType);
            var refreshToken = jwtService.generateRefreshTokenForUser(user, user.getName(), moduleType);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .user(userService.getDTO(user))
                    .build();
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            throw e;
        }
    }
}
