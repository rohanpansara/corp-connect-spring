package com.jwtauthentication.security.services;

import com.jwtauthentication.exceptions.client.LoginFailed;
import com.jwtauthentication.exceptions.client.RegistrationFailed;
import com.jwtauthentication.exceptions.client.UserNotFoundException;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.dtos.AuthRequestDTO;
import com.jwtauthentication.security.dtos.AuthResponseDTO;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.services.UserService;
import com.jwtauthentication.utils.EssConstants;
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

    public AuthResponseDTO addUser(RegisterDTO registerDTO) {

        try {
            var user = userService.getUserFromRegisterDTO(registerDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user.setLastUpdatedDate(LocalDateTime.now());
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
            user.setCreatedBy(String.valueOf(1));
            user.setLastUpdatedBy(String.valueOf(1));

            var savedUser = userRepository.save(user);
            var jwtToken = jwtService.generateTokenForUser(user, savedUser.getName(), "HR");
            var refreshToken = jwtService.generateRefreshTokenForUser(user, savedUser.getName(), "HR");
            return AuthResponseDTO.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .user(userService.getDTO(user))
                    .build();
        } catch (RegistrationFailed e) {
            throw new RegistrationFailed(EssConstants.UserError.EMAIL_EXISTS);
        } catch (RuntimeException e) {
            throw new LoginFailed(EssConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request, String moduleType) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new LoginFailed(EssConstants.UserError.USER_NOT_FOUND));

        var jwtToken = jwtService.generateTokenForUser(user, user.getName(), moduleType);
        var refreshToken = jwtService.generateRefreshTokenForUser(user, user.getName(), moduleType);
        return AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userService.getDTO(user))
                .build();

    }
}
