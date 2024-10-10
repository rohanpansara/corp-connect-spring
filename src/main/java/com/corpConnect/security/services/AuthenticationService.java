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
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
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

    public UserDTO addNewUser(NewUserDTO newUserDTO) {
        try {
            var user = userService.getUserFromRegisterDTO(newUserDTO);
            var savedUser = userService.finalSave(user);

            logger.info(LogConstants.getCreatedSuccessfullyMessage("User", "DTO", newUserDTO, "new user"));
            return userService.getDTO(savedUser);
        } catch (RegistrationFailedException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("User", "Email", newUserDTO.getEmail(), "while registering"));
            throw new RegistrationFailedException(MessageConstants.UserError.EMAIL_EXISTS);
        } catch (BaseException e) {
            logger.error("Did Not Match: Attempt to create a user with password: {} and confirmPassword: {}", newUserDTO.getPassword(), newUserDTO.getConfirmPassword());
            throw new RegistrationFailedException(MessageConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }


    public AuthResponseDTO authenticate(AuthRequestDTO request, String moduleType) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("User", "login", "Email", request.getEmail(), "while authenticating"));
                    logger.error(LogConstants.getLogInFailedMessage(request.getEmail(), null, "User Account Not Found"));
                    return new LoginFailedException(MessageConstants.UserError.USER_NOT_FOUND);
                });

        if (!user.isAccountEnabled()) {
            logger.error(LogConstants.getAccountActionTakenMessage(user.getEmail(), "Disabled", user.getId(), "while authenticating"));
            logger.error(LogConstants.getLogInFailedMessage(user.getEmail(), user.getId(), "User Account Is Disabled"));
            throw new LoginFailedException(MessageConstants.UserError.ACCOUNT_DISABLED);
        } else if (!user.isAccountNonLocked()) {
            logger.error(LogConstants.getAccountActionTakenMessage(user.getEmail(), "Locked", user.getId(), "while authenticating"));
            logger.error(LogConstants.getLogInFailedMessage(user.getEmail(), user.getId(), "User Account Is Locked"));
            throw new LoginFailedException(MessageConstants.UserError.ACCOUNT_LOCKED);
        }

        if (user.getLoginAttempts() >= 3) {
            user.setAccountNonLocked(false);
            user.setLoginAttempts(0);
            userRepository.save(user);
            logger.error(LogConstants.getAccountActionTakenMessage(user.getEmail(), "Locked", user.getId(), null));
            logger.error(LogConstants.getLogInFailedMessage(user.getEmail(), user.getId(), "Max Login Attempts Reached"));
            throw new LoginFailedException(MessageConstants.UserError.ACCOUNT_LOCKED);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userRepository.save(user);
            logger.error(LogConstants.getLogInFailedMessage(user.getEmail(), user.getId(), "Invalid Credentials"));
            throw new LoginFailedException(MessageConstants.UserError.INVALID_CREDENTIALS);
        }

        user.setLoginAttempts(0);
        User loggedUser = userRepository.save(user);

        var jwtToken = jwtService.generateTokenForUser(user, user.getEmail(), moduleType);
        EssUserContext.setCurrentUser(loggedUser);

        logger.info(LogConstants.getLoggedInSuccessMessage(loggedUser.getEmail(), loggedUser.getId()));
        return AuthResponseDTO.builder()
                .accessToken(jwtToken)
                .user(userService.getDTO(user))
                .build();
    }
}
