package com.corpConnect.controllers;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.AuthRequestDTO;
import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.security.services.AuthenticationService;
import com.corpConnect.services.CookieService;
import com.corpConnect.utils.constants.CookieConstants;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authenticationService;
    private final CookieUtils cookieUtils;
    private final CookieService cookieService;

    // TODO: remove this once the user table is final
    @PostMapping(value = "/new-user")
    public ResponseEntity<ResponseDTO<UserDTO>> addNewUser(@RequestBody NewUserDTO newUserDTO) throws LoginFailedException {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_CREATED, authenticationService.addNewUser(newUserDTO)));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> loginUser(@RequestBody AuthRequestDTO authRequestDTO) throws BaseException {
        AuthResponseDTO response = authenticationService.authenticate(authRequestDTO, "HR");

        // Generate cookies
        List<ResponseCookie> cookies = cookieService.generateAuthCookies(response);

        // Build response
        HttpHeaders headers = new HttpHeaders();
        cookies.forEach(cookie -> headers.add(HttpHeaders.SET_COOKIE, cookie.toString()));

        // Send response with data in headers and success message in body
        return ResponseEntity.ok()
                .headers(headers)
                .body(ResponseDTO.success(MessageConstants.UserSuccess.LOGIN_SUCCESS));
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> generateUserRefreshToken(@RequestParam("refresh-token") String refreshToken) {
        AuthResponseDTO response = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.TOKEN_REFRESHED, response));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<ResponseDTO<Void>> logoutUser() {
        // Clear cookies
        List<ResponseCookie> cookies = cookieService.clearAuthCookies();

        // Build response
        HttpHeaders headers = new HttpHeaders();
        cookies.forEach(cookie -> headers.add(HttpHeaders.SET_COOKIE, cookie.toString()));

        // Send response with data in headers and success message in body
        return ResponseEntity.ok()
                .headers(headers)
                .body(ResponseDTO.success(MessageConstants.UserSuccess.LOGOUT_SUCCESS));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ResponseDTO<Boolean>> validateUserToken(HttpServletRequest request) {
        String token = cookieUtils.getCookieValueByName(request, CookieConstants.TOKEN_COOKIE_NAME);
        String userId = cookieUtils.getCookieValueByName(request, CookieConstants.USER_ID_COOKIE_NAME);

        if (token != null && !token.isEmpty() && authenticationService.isTokenValid(token, userId)) {
            logger.info(LogConstants.getSessionVerifiedForToken(token, true));
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_SESSION_VERIFIED, true));
        }
        logger.error(LogConstants.getSessionVerifiedForToken(token, false));
        return ResponseEntity.badRequest().body(ResponseDTO.error(MessageConstants.UserError.USER_NOT_LOGGED_IN, false));
    }

}
