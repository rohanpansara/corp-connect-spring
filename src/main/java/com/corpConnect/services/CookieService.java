package com.corpConnect.services;

import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.utils.functions.CookieUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CookieService {

    private final CookieUtils cookieUtils;
    private final static String PUBLIC_PATH = "/";

    public CookieService(CookieUtils cookieUtils) {
        this.cookieUtils = cookieUtils;
    }

    public List<ResponseCookie> generateAuthCookies(AuthResponseDTO response) {
        ResponseCookie tokenCookie = cookieUtils.generateCookie("Token",
                URLEncoder.encode(response.getAccessToken(), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie userNameCookie = cookieUtils.generateCookie("User_Name",
                URLEncoder.encode(response.getUser().getName(), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie userIdCookie = cookieUtils.generateCookie("User_Id",
                URLEncoder.encode(String.valueOf(response.getUser().getId()), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie roleCookie = cookieUtils.generateCookie("User_Role",
                URLEncoder.encode(response.getUser().getRoles(), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie permissionCookie = cookieUtils.generateCookie("User_Permission",
                URLEncoder.encode(String.join("-", response.getUser().getPermissions()), StandardCharsets.UTF_8), PUBLIC_PATH);

        return List.of(tokenCookie, userNameCookie, userIdCookie, roleCookie, permissionCookie);
    }


    public List<ResponseCookie> clearAuthCookies(){
        ResponseCookie tokenCookie = cookieUtils.clearCookie("Token", "/api/auth");
        ResponseCookie roleCookie = cookieUtils.clearCookie("User_Role", "/api/auth");
        ResponseCookie permissionCookie = cookieUtils.clearCookie("User_Permission", "/api/auth");

        return List.of(tokenCookie, roleCookie, permissionCookie);
    }
}

