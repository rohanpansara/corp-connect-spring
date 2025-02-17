package com.corpConnect.services.company;

import com.corpConnect.entities.user.User;
import com.corpConnect.security.dtos.NewUserDTO;

public interface OTPService {

    String generateAndSaveNewUserOTPForUser(User user);

    void verifyNewUserOTP(Long userId, String otp);

}
