package com.corpConnect.security.utils;

import com.corpConnect.exceptions.client.RecordNotFoundException;
import com.corpConnect.utils.constants.CorpConnectConstants;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.corpConnect.security.utils.UserPermission.*;

@Getter
public enum UserRole {

    // For reference, PMS = Project Management Service

    ADMIN("Admin",Set.of(
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
            HR_MANAGER_CREATE,

            USER_READ,              // User rights
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    )),

    PMS_MANAGER("PMS Manager", Set.of(
            PMS_MANAGER_READ,
            PMS_MANAGER_UPDATE,
            PMS_MANAGER_DELETE,
            PMS_MANAGER_CREATE,

            USER_READ,              // User rights
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    )),

    HR_ADMIN("HR Admin", Set.of(
            HR_ADMIN_READ,
            HR_ADMIN_UPDATE,
            HR_ADMIN_DELETE,
            HR_ADMIN_CREATE,

            HR_MANAGER_READ,        // HR Manager rights
            HR_MANAGER_UPDATE,
            HR_MANAGER_DELETE,
            HR_MANAGER_CREATE,

            USER_READ,              // User rights
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    )),

    HR_MANAGER("HR Manager", Set.of(
            HR_MANAGER_READ,
            HR_MANAGER_UPDATE,
            HR_MANAGER_DELETE,
            HR_MANAGER_CREATE,

            USER_READ,              // User rights
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    )),

    USER("User", Set.of(
            USER_READ,
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    ));

    private final String label;
    private final Set<UserPermission> permissions;

    UserRole(String label, Set<UserPermission> permissions) {
        this.label = label;
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    public static UserRole getByLabel(String label) {
        for (UserRole role : values()) {
            if (role.getLabel().equalsIgnoreCase(label)) {
                return role;
            }
        }
        throw new RecordNotFoundException(CorpConnectConstants.UserError.USER_ROLE_NOT_FOUND);
    }
}
