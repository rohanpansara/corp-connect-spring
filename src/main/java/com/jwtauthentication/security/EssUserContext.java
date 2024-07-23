package com.jwtauthentication.security;

import com.jwtauthentication.entities.client.User;
import org.springframework.stereotype.Component;

@Component
public class EssUserContext {

    private EssUserContext() {}

    private static final ThreadLocal<User> loggedUser = ThreadLocal.withInitial(() -> null);

    public static User getCurrentUser() {
        return EssUserContext.loggedUser.get();
    }

    public static synchronized void setCurrentUser(User loggedUser) {
        EssUserContext.loggedUser.remove();
        EssUserContext.loggedUser.set(loggedUser);
    }
}
