package com.corpConnect.services.company.impl;

import com.corpConnect.entities.company.OTP;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.OTPType;
import com.corpConnect.repositories.company.OTPRepository;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.services.company.OTPService;
import com.corpConnect.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {

    private final OTPRepository otpRepository;
    private final UserService userService;

    @Override
    public String generateAndSaveNewUserOTPForUser(User user) {

        String otpString = this.generateOTP();
        OTP otp = new OTP(otpString, OTPType.NEW_USER_VERIFICATION, user, false, false);
        otpRepository.save(otp);

        return otpString;
    }

    @Override
    public void verifyNewUserOTP(Long userId, String otp) {
        Optional<OTP> optionalUnverifiedOTP = otpRepository.findByUserIdAndOtpAndNotVerified(userId, otp);
        User user = userService.getUserByUserId(userId);

        if (optionalUnverifiedOTP.isEmpty()) {
            throw new RuntimeException("OTP doesn't match. Please try again.");
        }

        OTP foundOTP = optionalUnverifiedOTP.get();

        if (otp.equalsIgnoreCase(foundOTP.getOtp())) {
            foundOTP.setVerified(true);
            user.setEmailVerified(true);
            otpRepository.save(foundOTP);
            userService.save(user);
        } else {
            throw new RuntimeException("OTP doesn't match. Please try again.");
        }
    }

    @Override
    public boolean validateForPendingOTPVerification(Long userId, OTPType otpType) {
        List<OTP> otpList = otpRepository.findByUserIdAndOtpTypeAndNotVerified(userId, otpType);

        // it will return true if no pending otp found
        return otpList.isEmpty();
    }

    private String generateOTP() {
        Random random = new Random();
        return String.valueOf(100_000 + random.nextInt(900_000));
    }
}
