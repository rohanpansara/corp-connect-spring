package com.jwtauthentication.security.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jwtauthentication.security.utils.UserPermission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    // For reference, PMS = Project Management Service

    ADMIN(Set.of(
            ADMIN_READ,             // Admin rights
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE,

            PMS_MANAGER_READ,           // PMS Manager rights
            PMS_MANAGER_UPDATE,
            PMS_MANAGER_DELETE,
            PMS_MANAGER_CREATE,

            HR_ADMIN_READ,          // HR Admin rights
            HR_ADMIN_UPDATE,
            HR_ADMIN_DELETE,
            HR_ADMIN_CREATE,

            HR_MANAGER_READ,        // HR Manager rights
            HR_MANAGER_UPDATE,
            HR_MANAGER_DELETE,
            HR_MANAGER_CREATE
    )),

    PMS_MANAGER(Set.of(
            PMS_MANAGER_READ,
            PMS_MANAGER_UPDATE,
            PMS_MANAGER_DELETE,
            PMS_MANAGER_CREATE
    )),

    HR_ADMIN(Set.of(
            HR_ADMIN_READ,
            HR_ADMIN_UPDATE,
            HR_ADMIN_DELETE,
            HR_ADMIN_CREATE,
            HR_MANAGER_READ,
            HR_MANAGER_UPDATE,
            HR_MANAGER_DELETE,
            HR_MANAGER_CREATE
    )),

    HR_MANAGER(Set.of(
            HR_MANAGER_READ,
            HR_MANAGER_UPDATE,
            HR_MANAGER_DELETE,
            HR_MANAGER_CREATE
    )),

    USER(Set.of(
            USER_READ,
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    ));

    private final Set<UserPermission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
