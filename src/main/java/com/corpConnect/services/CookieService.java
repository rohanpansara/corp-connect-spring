package com.corpConnect.services;

import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.utils.functions.CookieUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookieService {

    private final CookieUtils cookieUtils;

    public CookieService(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
    }

    public List<ResponseCookie> generateAuthCookies(AuthResponseDTO response) {
        ResponseCookie tokenCookie = cookieUtils.generateCookie("Token", response.getAccessToken(), "/api/auth");
        ResponseCookie roleCookie = cookieUtils.generateCookie("User_Role", response.getRefreshToken(), "/api/auth");
        ResponseCookie permissionCookie = cookieUtils.generateCookie("User_Permission", String.join("-", response.getUser().getPermissions()), "/api/auth");

        return List.of(tokenCookie, roleCookie, permissionCookie);
    }

    public List<ResponseCookie> clearAuthCookies(){
        ResponseCookie tokenCookie = cookieUtils.clearCookie("Token", "/api/auth");
        ResponseCookie roleCookie = cookieUtils.clearCookie("User_Role", "/api/auth");
        ResponseCookie permissionCookie = cookieUtils.clearCookie("User_Permission", "/api/auth");

        return List.of(tokenCookie, roleCookie, permissionCookie);
    }
}

