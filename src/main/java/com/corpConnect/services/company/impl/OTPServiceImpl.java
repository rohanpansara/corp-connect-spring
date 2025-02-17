package com.corpConnect.services.company.impl;

import com.corpConnect.entities.company.OTP;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.OTPType;
import com.corpConnect.repositories.company.OTPRepository;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.services.company.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {

    private final OTPRepository otpRepository;

    @Override
    public String generateAndSaveNewUserOTPForUser(User user) {

        String otpString = this.generateOTP();
        OTP otp = new OTP(otpString, OTPType.NEW_USER_VERIFICATION, user, false, false);
        otpRepository.save(otp);

        return otpString;
    }

    @Override
    public void verifyNewUserOTP(Long userId, String otp) {
        Optional<OTP> unverifiedOTP = otpRepository.findByUserIdAndOtpAndNotVerified(userId, otp);

        if (unverifiedOTP.isEmpty()) {
            throw new RuntimeException("OTP doesn't match. Please try again.");
        }

        OTP foundOTP = unverifiedOTP.get();

        if (otp.equalsIgnoreCase(foundOTP.getOtp())) {
            foundOTP.setVerified(true);
            otpRepository.save(foundOTP);
        } else {
            throw new RuntimeException("OTP doesn't match. Please try again.");
        }
    }

    private String generateOTP() {
        Random random = new Random();
        return String.valueOf(100_000 + random.nextInt(900_000));
    }
}
