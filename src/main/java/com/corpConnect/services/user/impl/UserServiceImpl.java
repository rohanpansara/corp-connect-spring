package com.corpConnect.services.user.impl;

import com.corpConnect.audits.ApplicationAuditAware;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.dtos.user.card.CardDataDTO;
import com.corpConnect.dtos.user.card.DashboardCardDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.UserNotFoundException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.mappers.client.UserMapper;
import com.corpConnect.repositories.user.UserRepository;
import com.corpConnect.security.dtos.RegisterDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.constants.LogConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final ApplicationAuditAware applicationAuditAware;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException {
        return userMapper.toEntityFromRegisterDTO(registerDTO);
    }

    @Override
    public User getEntity(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    @Override
    public UserDTO getDTO(User user) {
        return userMapper.toDTO(user);
    }

    @Override
    public List<User> getEntityList(List<UserDTO> userDTOList) {
        return userMapper.toEntityList(userDTOList);
    }

    @Override
    public List<UserDTO> getDTOList(List<User> userList) {
        return userMapper.toDTOList(userList);
    }

    @Override
    @Transactional
    public User finalSave(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        Optional<String> currentAuditor = applicationAuditAware.getCurrentAuditor();
        if (currentAuditor.isEmpty()) {
            user.setCreatedBy("system");
            user.setLastUpdatedBy("system");
            logger.info(LogConstants.getAuditorNotFoundMessage("User", user.getId(), "while creating"));
        } else {
            String auditor = currentAuditor.get();
            user.setCreatedBy(auditor);
            user.setLastUpdatedBy(auditor);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        return foundUser.orElse(null);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public List<User> getUserByAccountExpiration(Boolean isExpired) {
        return userRepository.findByIsAccountNonExpired(isExpired);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllNonDeletedUsers() {
        return userRepository.findByIsDeleted(false);
    }

    @Override
    public List<User> getAllDeletedUsers() {
        return userRepository.findByIsDeleted(true);
    }

    @Override
    public DashboardCardDTO getDashboardCards() {
        DashboardCardDTO dashboardCard = new DashboardCardDTO();
        dashboardCard.setDailyHoursCard(new CardDataDTO(
                "Time Worked Today",
                "4h 45m",
                "24 hours logged this week"
        ));
        dashboardCard.setShiftDetailsCard(new CardDataDTO(
                "Shift Details",
                "7h 30m",
                "General Shift | 8AM to 10PM"
        ));
        dashboardCard.setLeaveDetailsCard(new CardDataDTO(
                "Leaves Available",
                "12",
                "3 leaves taken last month"
        ));
        dashboardCard.setUpcomingMeetingCard(new CardDataDTO(
                "Upcoming Meeting",
                "Daily Scrum @ 3PM",
                "Place: Conference room"
        ));
        return dashboardCard;
    }


    @Override
    @Transactional
    public void unlockUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to unlock user with id: {}", userId);
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                }
        );
        if (user.isAccountNonLocked()) {
            logger.error("Already Updated: Attempt to unlock user with id: {}", userId);
            return;
        }
        user.setAccountNonLocked(true);
        userRepository.saveAndFlush(user);
        logger.error("Updated: Attempt to unlock with id: {} by user with id: {}", userId, applicationAuditAware.getCurrentAuditor().orElse("system"));
    }

    @Override
    @Transactional
    public void enableUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to enable user with id: {}", userId);
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                }
        );
        if (user.isAccountEnabled()) {
            logger.error("Already Updated: Attempt to enable user with id: {}", userId);
            return;
        }
        user.setAccountEnabled(true);
        userRepository.saveAndFlush(user);
        logger.error("Updated: Attempt to enable with id: {} by user with id: {}", userId, applicationAuditAware.getCurrentAuditor().orElse("system"));
    }

    @Override
    @Transactional
    public void disableUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to disable user with id: {}", userId);
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                }
        );
        if (!user.isAccountEnabled()) {
            logger.error("Already Updated: Attempt to disable user with id: {}", userId);
            return;
        }
        user.setAccountEnabled(false);
        userRepository.saveAndFlush(user);
        logger.error("Updated: Attempt to disable with id: {} by user with id: {}", userId, applicationAuditAware.getCurrentAuditor().orElse("system"));
    }

    @Override
    @Transactional
    public void lockUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to lock user with id: {}", userId);
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                }
        );
        if (!user.isAccountNonLocked()) {
            logger.error("Already Updated: Attempt to lock user with id: {}", userId);
            return;
        }
        user.setAccountNonLocked(false);
        userRepository.saveAndFlush(user);
        logger.error("Updated: Attempt to lock with id: {} by user with id: {}", userId, applicationAuditAware.getCurrentAuditor().orElse("system"));
    }
}
