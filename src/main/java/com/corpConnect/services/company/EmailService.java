package com.corpConnect.services.company;

public interface EmailService {

    void sendWelcomeEmail(String newUserEmail, String newUserName);

    void sendNewUserEmail(String newUserEmail, String newUserName);

}
