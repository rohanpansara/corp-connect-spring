package com.jwtauthentication.security.services;

import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.LoginFailed;
import com.jwtauthentication.exceptions.client.RegistrationFailed;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.dtos.AuthRequestDTO;
import com.jwtauthentication.security.dtos.AuthResponseDTO;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.services.UserService;
import com.jwtauthentication.utils.EssConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO addUser(RegisterDTO registerDTO) {

        try {
            var user = userService.getUserFromRegisterDTO(registerDTO);
            var savedUser = userService.finalSave(user);
            var jwtToken = jwtService.generateTokenForUser(user, savedUser.getEmail(), "HR");
            return AuthResponseDTO.builder()
                    .accessToken(jwtToken)
                    .user(userService.getDTO(user))
                    .build();
        } catch (RegistrationFailed e) {
            throw new RegistrationFailed(EssConstants.UserError.EMAIL_EXISTS);
        } catch (BaseException e) {
            throw new RegistrationFailed(EssConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request, String moduleType) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new LoginFailed(EssConstants.UserError.USER_NOT_FOUND));

        if (!user.isAccountEnabled()) {
            throw new LoginFailed(EssConstants.UserError.ACCOUNT_DISABLED);
        } else if (!user.isAccountNonLocked()) {
            throw new LoginFailed(EssConstants.UserError.ACCOUNT_LOCKED);
        }

        if (user.getLoginAttempts() >= 3) {
            user.setAccountNonLocked(false);
            user.setLoginAttempts(0);
            userRepository.save(user);
            throw new LoginFailed(EssConstants.UserError.ACCOUNT_LOCKED);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userRepository.save(user);
            throw new LoginFailed(EssConstants.UserError.INVALID_CREDENTIALS);
        }

        user.setLoginAttempts(0);
        userRepository.save(user);

        var jwtToken = jwtService.generateTokenForUser(user, user.getEmail(), moduleType);
        var refreshToken = jwtService.generateRefreshTokenForUser(user, user.getEmail(), moduleType);

//        EssUserContext.setCurrentUser(user);

        return AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userService.getDTO(user))
                .build();
    }
}
