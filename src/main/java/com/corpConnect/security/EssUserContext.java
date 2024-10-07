package com.corpConnect.security;

import com.corpConnect.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class EssUserContext {

    private EssUserContext() {}

    private static final ThreadLocal<User> loggedUser = ThreadLocal.withInitial(() -> null);

    public static User getCurrentUser() {
        return EssUserContext.loggedUser.get();
    }

    public static synchronized void setCurrentUser(User user) {
        EssUserContext.loggedUser.set(user);
    }

    public static void clear() {
        EssUserContext.loggedUser.remove();
    }
}
