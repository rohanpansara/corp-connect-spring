package com.corpConnect.services.company;

import com.corpConnect.entities.user.User;

public interface EmailService {

    void sendWelcomeEmail(String newUserEmail, String newUserName);

    void sendNewUserEmail(User user);

}
