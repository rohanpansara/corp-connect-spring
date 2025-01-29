package com.corpConnect.services;

import com.corpConnect.security.dtos.AuthResponseDTO;
import com.corpConnect.utils.constants.CookieConstants;
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
        ResponseCookie tokenCookie = cookieUtils.generateCookie(CookieConstants.TOKEN_COOKIE_NAME,
                URLEncoder.encode(response.getAccessToken(), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie userNameCookie = cookieUtils.generateCookie(CookieConstants.USER_NAME_COOKIE_NAME,
                URLEncoder.encode(response.getUser().getName(), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie userIdCookie = cookieUtils.generateCookie(CookieConstants.USER_ID_COOKIE_NAME,
                URLEncoder.encode(String.valueOf(response.getUser().getId()), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie roleCookie = cookieUtils.generateCookie(CookieConstants.USER_ROLE_COOKIE_NAME,
                URLEncoder.encode(response.getUser().getRoles(), StandardCharsets.UTF_8), PUBLIC_PATH);
        ResponseCookie permissionCookie = cookieUtils.generateCookie(CookieConstants.USER_PERMISSION_COOKIE_NAME,
                URLEncoder.encode(String.join("-", response.getUser().getPermissions()), StandardCharsets.UTF_8), PUBLIC_PATH);

        return List.of(tokenCookie, userNameCookie, userIdCookie, roleCookie, permissionCookie);
    }


    public List<ResponseCookie> clearAuthCookies(){
        ResponseCookie tokenCookie = cookieUtils.clearCookie(CookieConstants.TOKEN_COOKIE_NAME, PUBLIC_PATH);
        ResponseCookie userIdCookie = cookieUtils.clearCookie(CookieConstants.USER_ID_COOKIE_NAME, PUBLIC_PATH);
        ResponseCookie userNameCookie = cookieUtils.clearCookie(CookieConstants.USER_NAME_COOKIE_NAME, PUBLIC_PATH);
        ResponseCookie roleCookie = cookieUtils.clearCookie(CookieConstants.USER_ROLE_COOKIE_NAME, PUBLIC_PATH);
        ResponseCookie permissionCookie = cookieUtils.clearCookie(CookieConstants.USER_PERMISSION_COOKIE_NAME, PUBLIC_PATH);

        return List.of(tokenCookie, roleCookie, permissionCookie);
    }
}

