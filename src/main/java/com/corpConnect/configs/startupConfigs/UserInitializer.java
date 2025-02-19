package com.corpConnect.configs.startupConfigs;

import com.corpConnect.entities.user.User;
import com.corpConnect.repositories.user.UserRepository;
import com.corpConnect.security.utils.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class UserInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${user-initializer.name}")
    private String userName;

    @Value("${user-initializer.email}")
    private String userEmail;

    @Value("${user-initializer.password}")
    private String userPassword;

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.findByRoles(UserRole.ADMIN).isEmpty()) {
            User admin = new User();
            admin.setName(userName);
            admin.setEmail(userEmail);
            admin.setPassword(passwordEncoder.encode(userPassword));
            admin.setRoles(UserRole.ADMIN);
            admin.setEmailVerified(true);
            admin.setAccountEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            admin.setCreatedBy("system-start");
            admin.setLastUpdatedBy("system-start");
            admin.setCreatedDate(LocalDateTime.now());
            admin.setLastUpdatedDate(LocalDateTime.now());
            userRepository.save(admin);
            logger.info("*** ADMIN USER CREATED ***");
        }
    }
}

