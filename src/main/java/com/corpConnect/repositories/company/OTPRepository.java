package com.corpConnect.repositories.company;

import com.corpConnect.entities.company.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {

    @Query("SELECT o FROM OTP o WHERE o.user.id = :userId AND o.otp = :otp AND o.verified = false")
    Optional<OTP> findByUserIdAndOtpAndNotVerified(@Param("userId") Long userId, @Param("otp") String otp);

}
