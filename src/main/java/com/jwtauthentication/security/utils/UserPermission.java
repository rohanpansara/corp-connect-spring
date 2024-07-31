package com.jwtauthentication.security.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserPermission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    PMS_MANAGER_READ("pms_manager:read"),
    PMS_MANAGER_UPDATE("pms_manager:update"),
    PMS_MANAGER_CREATE("pms_manager:create"),
    PMS_MANAGER_DELETE("pms_manager:delete"),

    HR_ADMIN_READ("hr_admin:read"),
    HR_ADMIN_UPDATE("hr_admin:update"),
    HR_ADMIN_CREATE("hr_admin:create"),
    HR_ADMIN_DELETE("hr_admin:delete"),

    HR_MANAGER_READ("hr_manager:read"),
    HR_MANAGER_UPDATE("hr_manager:update"),
    HR_MANAGER_CREATE("hr_manager:create"),
    HR_MANAGER_DELETE("hr_manager:delete"),


    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete");

    @Getter
    private final String permission;
}
