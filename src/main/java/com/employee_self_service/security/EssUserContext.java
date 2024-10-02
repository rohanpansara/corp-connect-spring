package com.employee_self_service.security;

import com.employee_self_service.entities.users.Users;
import org.springframework.stereotype.Component;

@Component
public class EssUserContext {

    private EssUserContext() {}

    private static final ThreadLocal<Users> loggedUser = ThreadLocal.withInitial(() -> null);

    public static Users getCurrentUser() {
        return EssUserContext.loggedUser.get();
    }

    public static synchronized void setCurrentUser(Users users) {
        EssUserContext.loggedUser.set(users);
    }

    public static void clear() {
        EssUserContext.loggedUser.remove();
    }
}
