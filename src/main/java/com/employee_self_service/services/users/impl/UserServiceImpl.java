package com.employee_self_service.services.users.impl;

import com.employee_self_service.audits.ApplicationAuditAware;
import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.users.card.CardDataDTO;
import com.employee_self_service.dtos.users.card.DashboardCardDTO;
import com.employee_self_service.entities.users.Users;
import com.employee_self_service.exceptions.client.UserNotFoundException;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.mappers.client.UserMapper;
import com.employee_self_service.repositories.users.UserRepository;
import com.employee_self_service.security.dtos.RegisterDTO;
import com.employee_self_service.services.users.UserService;
import com.employee_self_service.utils.constants.EssConstants;
import com.employee_self_service.utils.constants.LogConstants;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final ApplicationAuditAware applicationAuditAware;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, ApplicationAuditAware applicationAuditAware, UserMapper userMapper, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationAuditAware = applicationAuditAware;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Users getUserFromRegisterDTO(RegisterDTO registerDTO) throws BaseException {
        return userMapper.toEntityFromRegisterDTO(registerDTO);
    }

    @Override
    public Users getEntity(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    @Override
    public UserDTO getDTO(Users users) {
        return userMapper.toDTO(users);
    }

    @Override
    public List<Users> getEntityList(List<UserDTO> userDTOList) {
        return userMapper.toEntityList(userDTOList);
    }

    @Override
    public List<UserDTO> getDTOList(List<Users> usersList) {
        return userMapper.toDTOList(usersList);
    }

    @Override
    @Transactional
    public Users finalSave(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setAccountNonExpired(true);
        users.setCredentialsNonExpired(true);
        Optional<String> currentAuditor = applicationAuditAware.getCurrentAuditor();
        if (currentAuditor.isEmpty()) {
            users.setCreatedBy("system");
            users.setLastUpdatedBy("system");
            logger.error(LogConstants.getAuditorNotFoundMessage("User", users.getId(), "while creating"));
        } else {
            String auditor = currentAuditor.get();
            users.setCreatedBy(auditor);
            users.setLastUpdatedBy(auditor);
        }
        return userRepository.save(users);
    }

    @Override
    public Users getUserByEmail(String email) {
        Optional<Users> foundUser = userRepository.findByEmail(email);
        if(foundUser.isEmpty()){
            logger.error(LogConstants.getNotFoundMessage("User", "get", "Email", email, null));
            return null;
        }
        return foundUser.get();
    }

    @Override
    public Users getUserByUserId(Long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public List<Users> getUserByAccountExpiration(Boolean isExpired) {
        return userRepository.findByIsAccountNonExpired(isExpired);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
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
        Users user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to unlock user with id: {}", userId);
                    return new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND);
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
        Users user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to enable user with id: {}", userId);
                    return new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND);
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
        Users user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to disable user with id: {}", userId);
                    return new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND);
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
        Users user = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to lock user with id: {}", userId);
                    return new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND);
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
