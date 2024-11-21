package com.corpConnect.controllers;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.AuthRequestDTO;
import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.security.services.AuthenticationService;
import com.corpConnect.services.CookieService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> loginUser(@RequestBody AuthRequestDTO authRequestDTO) throws BaseException {
        AuthResponseDTO response = authenticationService.authenticate(authRequestDTO, "HR");

        // Generate cookies
        List<ResponseCookie> cookies = cookieService.generateAuthCookies(response);

        // Clear sensitive data using abstraction
        response.clearSensitiveData();

        // Build response
        HttpHeaders headers = new HttpHeaders();
        cookies.forEach(cookie -> headers.add(HttpHeaders.SET_COOKIE, cookie.toString()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(ResponseDTO.success(MessageConstants.UserSuccess.LOGIN_SUCCESS, response));
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

        return ResponseEntity.ok()
                .headers(headers)
                .body(ResponseDTO.success(MessageConstants.UserSuccess.LOGOUT_SUCCESS));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ResponseDTO<Boolean>> validateUserToken(HttpServletRequest request) {
        String token = cookieUtils.getCookieValueByName(request, "Token");

        if (token != null && !token.isEmpty() && authenticationService.isTokenValid(token)) {
            logger.info(LogConstants.getSessionVerifiedForToken(token, true));
            return ResponseEntity.ok(ResponseDTO.success(MessageConstants.UserSuccess.USER_SESSION_VERIFIED, true));
        }
        logger.error(LogConstants.getSessionVerifiedForToken(token, false));
        return ResponseEntity.badRequest().body(ResponseDTO.error(MessageConstants.UserError.USER_NOT_LOGGED_IN, false));
    }

}
