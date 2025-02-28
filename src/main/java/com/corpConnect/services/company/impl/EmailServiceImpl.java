package com.corpConnect.services.company.impl;

import com.corpConnect.entities.company.Configuration;
import com.corpConnect.entities.company.EmailTemplate;
import com.corpConnect.entities.user.User;
import com.corpConnect.repositories.company.EmailTemplateRepository;
import com.corpConnect.services.company.ConfigurationService;
import com.corpConnect.services.company.EmailService;
import com.corpConnect.services.company.OTPService;
import com.corpConnect.utils.constants.CommonConstants;
import com.corpConnect.utils.constants.LogConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final ConfigurationService configurationService;
    private final JavaMailSender javaMailSender;
    private final EmailTemplateRepository emailTemplateRepository;
    private final OTPService otpService;

    @Async
    @Override
    public void sendWelcomeEmail(String toEmail, String name) {
        Optional<EmailTemplate> template = emailTemplateRepository.findByName("welcome_user");
        if (template.isEmpty()) {
            logger.error("'Welcome User' email template not found");
            return;
        }

        EmailTemplate welcomeUserEmailTemplate = template.get();

        String subject = welcomeUserEmailTemplate.getSubject()
                .replace("{{employeeName}}", name);

        String body = welcomeUserEmailTemplate.getBody()
                .replace("{{employeeName}}", name)
                .replace("{{companyName}}", CommonConstants.COMPANY_NAME)
                .replace("{{employeePortalLink}}", "https://employeeportal.com")
                .replace("{{onboardingLink}}", "https://onboarding.com")
                .replace("{{contactHRLink}}", "mailto:hr@company.com")
                .replace("{{currentYear}}", String.valueOf(LocalDate.now().getYear()));

        try {
            sendEmail(toEmail, subject, body);
            logger.info(LogConstants.getWelcomeMailSentSuccessfullyMessage(toEmail));
        } catch (Exception e) {
            logger.error(LogConstants.getEmailNotSentMessage("user", "add", toEmail, e.getLocalizedMessage()));
        }
    }

    @Async
    @Override
    public void sendNewUserEmail(User user) {
        Optional<EmailTemplate> template = emailTemplateRepository.findByName("new_user_otp_verification");
        if (template.isEmpty()) {
            logger.error("'OTP' email template not found");
            return;
        }

        String otp = otpService.generateAndSaveNewUserOTPForUser(user);

        String subject = template.get().getSubject();
        String body = template.get().getBody()
                .replace("{{otp}}", otp)
                .replace("{{userId}}", String.valueOf(user.getId()))
                .replace("{{currentYear}}", String.valueOf(LocalDate.now().getYear()))
                .replace("{{companyName}}", CommonConstants.COMPANY_NAME);

        try {
            sendEmail(user.getEmail(), subject, body);
            logger.info(LogConstants.getNewUserOTPMailSentSuccessfullyMessage(user.getEmail()));
        } catch (Exception e) {
            logger.error(LogConstants.getEmailNotSentMessage("user", "new", user.getEmail(), e.getLocalizedMessage()));
        }
    }

    private void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Configuration mailConfiguration = configurationService.getNonDeletedConfigurationByName("EMAIL_ENABLED");
        if (mailConfiguration != null && !mailConfiguration.isEnabled()) {
            logger.info("Email configuration is disabled");
            return;
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }
}
