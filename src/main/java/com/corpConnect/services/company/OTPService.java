package com.corpConnect.services.company;

import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.OTPType;

public interface OTPService {

    String generateAndSaveNewUserOTPForUser(User user);

    void verifyNewUserOTP(Long userId, String otp);

    boolean validateForPendingOTPVerification(Long userId, OTPType otpType);
}
