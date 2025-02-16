package com.corpConnect.services.company.impl;

import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.services.company.OTPService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPServiceImpl implements OTPService {

    @Override
    public String generateOTP() {
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }

    @Override
    public void generateNewUserOTPAndSendEmail(NewUserDTO newUserDTO) {

    }
}
