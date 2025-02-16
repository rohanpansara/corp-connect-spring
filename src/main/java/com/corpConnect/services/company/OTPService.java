package com.corpConnect.services.company;

import com.corpConnect.security.dtos.NewUserDTO;

public interface OTPService {

    String generateOTP();

    void generateNewUserOTPAndSendEmail(NewUserDTO newUserDTO);

}
