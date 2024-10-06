package com.corpConnect.security.services;

import com.corpConnect.dtos.users.UserDTO;
import com.corpConnect.entities.users.Users;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.client.RegistrationFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.repositories.users.UserRepository;
import com.corpConnect.security.EssUserContext;
import com.corpConnect.security.dtos.AuthRequestDTO;
import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.security.dtos.RegisterDTO;
import com.corpConnect.services.users.UserService;
import com.corpConnect.utils.constants.CorpConnectConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDTO addUser(RegisterDTO registerDTO) {
        try {
            var user = userService.getUserFromRegisterDTO(registerDTO);
            var savedUser = userService.finalSave(user);
            logger.info("Created: Attempt to create a user with registerDto: {}", registerDTO);
            return userService.getDTO(savedUser);
        } catch (RegistrationFailedException e) {
            logger.error("Already Exists: Attempt to create a user with email: {}", registerDTO.getEmail());
            throw new RegistrationFailedException(CorpConnectConstants.UserError.EMAIL_EXISTS);
        } catch (BaseException e) {
            logger.error("Did Not Match: Attempt to create a user with password: {} and confirmPassword: {}", registerDTO.getPassword(), registerDTO.getConfirmPassword());
            throw new RegistrationFailedException(CorpConnectConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }


    public AuthResponseDTO authenticate(AuthRequestDTO request, String moduleType) {

        Users users = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.error("Not Found: Attempt to authenticate user with email: {}", request.getEmail());
                    return new LoginFailedException(CorpConnectConstants.UserError.USER_NOT_FOUND);
                });

        if (!users.isAccountEnabled()) {
            logger.error("Account Disabled: Attempt to authenticate user with email: {}", request.getEmail());
            throw new LoginFailedException(CorpConnectConstants.UserError.ACCOUNT_DISABLED);
        } else if (!users.isAccountNonLocked()) {
            logger.error("Account Locked: Attempt to authenticate user with email: {}", request.getEmail());
            throw new LoginFailedException(CorpConnectConstants.UserError.ACCOUNT_LOCKED);
        }

        if (users.getLoginAttempts() >= 3) {
            users.setAccountNonLocked(false);
            users.setLoginAttempts(0);
            userRepository.save(users);
            logger.error("Account Locked: Max login attempt limit exceeded for user with email: {}", request.getEmail());
            throw new LoginFailedException(CorpConnectConstants.UserError.ACCOUNT_LOCKED);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            users.setLoginAttempts(users.getLoginAttempts() + 1);
            userRepository.save(users);
            logger.error("Invalid Credentials: Attempt to login with email: {}", request.getEmail());
            throw new LoginFailedException(CorpConnectConstants.UserError.INVALID_CREDENTIALS);
        }

        users.setLoginAttempts(0);
        Users loggedUsers = userRepository.save(users);

        var jwtToken = jwtService.generateTokenForUser(users, users.getEmail(), moduleType);
        EssUserContext.setCurrentUser(loggedUsers);

        return AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .user(userService.getDTO(users))
                .build();
    }
}
