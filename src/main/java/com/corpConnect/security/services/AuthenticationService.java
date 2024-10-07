package com.corpConnect.security.services;

import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.client.RegistrationFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.repositories.user.UserRepository;
import com.corpConnect.security.EssUserContext;
import com.corpConnect.security.dtos.AuthRequestDTO;
import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.security.dtos.RegisterDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.CorpConnectConstants;
import com.corpConnect.utils.constants.LogConstants;
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
            logger.info(LogConstants.getCreatedSuccessfullyMessage("User", "DTO", registerDTO, "new user"));
            return userService.getDTO(savedUser);
        } catch (RegistrationFailedException e) {
            logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("User", "Email", registerDTO.getEmail(), "while registering"));
            throw new RegistrationFailedException(CorpConnectConstants.UserError.EMAIL_EXISTS);
        } catch (BaseException e) {
            logger.error("Did Not Match: Attempt to create a user with password: {} and confirmPassword: {}", registerDTO.getPassword(), registerDTO.getConfirmPassword());
            throw new RegistrationFailedException(CorpConnectConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }


    public AuthResponseDTO authenticate(AuthRequestDTO request, String moduleType) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("User", "login", "Email", request.getEmail(), "while authenticating"));
                    return new LoginFailedException(CorpConnectConstants.UserError.USER_NOT_FOUND);
                });

        if (!user.isAccountEnabled()) {
            logger.error(LogConstants.getAccountDisabledMessage(user.getEmail(), user.getId(), "while authenticating"));
            throw new LoginFailedException(CorpConnectConstants.UserError.ACCOUNT_DISABLED);
        } else if (!user.isAccountNonLocked()) {
            logger.error(LogConstants.getAccountLockedMessage(user.getEmail(), user.getId(), "while authenticating"));
            throw new LoginFailedException(CorpConnectConstants.UserError.ACCOUNT_LOCKED);
        }

        if (user.getLoginAttempts() >= 3) {
            user.setAccountNonLocked(false);
            user.setLoginAttempts(0);
            userRepository.save(user);
            logger.error(LogConstants.getMaxLoginReachedMessage(user.getEmail(), user.getId()));
            throw new LoginFailedException(CorpConnectConstants.UserError.ACCOUNT_LOCKED);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userRepository.save(user);
            logger.error(LogConstants.getInvalidCredentialMessage(user.getEmail(), user.getId()));
            throw new LoginFailedException(CorpConnectConstants.UserError.INVALID_CREDENTIALS);
        }

        user.setLoginAttempts(0);
        User loggedUser = userRepository.save(user);

        var jwtToken = jwtService.generateTokenForUser(user, user.getEmail(), moduleType);
        EssUserContext.setCurrentUser(loggedUser);

        logger.info(LogConstants.getLoggedInMessage(loggedUser.getEmail(), loggedUser.getId()));
        return AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .user(userService.getDTO(user))
                .build();
    }
}
