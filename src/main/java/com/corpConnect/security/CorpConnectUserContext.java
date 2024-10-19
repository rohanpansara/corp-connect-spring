package com.corpConnect.security;

import com.corpConnect.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class CorpConnectUserContext {

    private CorpConnectUserContext() {}

    private static final ThreadLocal<User> loggedUser = ThreadLocal.withInitial(() -> null);

    public static User getCurrentUser() {
        return CorpConnectUserContext.loggedUser.get();
    }

    public static synchronized void setCurrentUser(User user) {
        CorpConnectUserContext.loggedUser.set(user);
    }

    public static void clear() {
        CorpConnectUserContext.loggedUser.remove();
    }
}
