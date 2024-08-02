package com.jwtauthentication.security.services;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.LoginFailedException;
import com.jwtauthentication.exceptions.client.RegistrationFailedException;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.EssUserContext;
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

    public UserDTO addUser(RegisterDTO registerDTO) {
        try {
            var user = userService.getUserFromRegisterDTO(registerDTO);
            var savedUser = userService.finalSave(user);
            return userService.getDTO(savedUser);
        } catch (RegistrationFailedException e) {
            throw new RegistrationFailedException(EssConstants.UserError.EMAIL_EXISTS);
        } catch (BaseException e) {
            throw new RegistrationFailedException(EssConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request, String moduleType) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new LoginFailedException(EssConstants.UserError.USER_NOT_FOUND));

        if (!user.isAccountEnabled()) {
            throw new LoginFailedException(EssConstants.UserError.ACCOUNT_DISABLED);
        } else if (!user.isAccountNonLocked()) {
            throw new LoginFailedException(EssConstants.UserError.ACCOUNT_LOCKED);
        }

        if (user.getLoginAttempts() >= 3) {
            user.setAccountNonLocked(false);
            user.setLoginAttempts(0);
            userRepository.save(user);
            throw new LoginFailedException(EssConstants.UserError.ACCOUNT_LOCKED);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userRepository.save(user);
            throw new LoginFailedException(EssConstants.UserError.INVALID_CREDENTIALS);
        }

        user.setLoginAttempts(0);
        User loggedUser = userRepository.save(user);

        var jwtToken = jwtService.generateTokenForUser(user, user.getEmail(), moduleType);
        EssUserContext.setCurrentUser(loggedUser);

        return AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .user(userService.getDTO(user))
                .build();
    }
}
