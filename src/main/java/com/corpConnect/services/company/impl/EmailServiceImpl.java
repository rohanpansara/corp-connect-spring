package com.corpConnect.services.company.impl;

import com.corpConnect.entities.company.EmailTemplate;
import com.corpConnect.repositories.company.EmailTemplateRepository;
import com.corpConnect.services.company.EmailService;
import com.corpConnect.utils.constants.LogConstants;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;
    private final EmailTemplateRepository emailTemplateRepository;

    @Async
    @Override
    public void sendWelcomeEmail(String toEmail, String name) {
        EmailTemplate template = emailTemplateRepository.findByName("welcome");
        String subject = template.getSubject()
                .replace("{{employeeName}}", name);

        String body = template.getBody()
                .replace("{{employeeName}}", name)
                .replace("{{companyName}}", "Corp Connect")
                .replace("{{employeePortalLink}}", "https://employeeportal.com")
                .replace("{{onboardingLink}}", "https://onboarding.com")
                .replace("{{contactHRLink}}", "mailto:hr@company.com")
                .replace("{{currentYear}}", String.valueOf(LocalDate.now().getYear()));

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
            logger.info(LogConstants.getWelcomeMailSentSuccessfullyMessage(toEmail));
        } catch (Exception e) {
            logger.error(LogConstants.getEmailNotSentMessage("user", "add", toEmail, e.getLocalizedMessage()));
        }
    }
}
