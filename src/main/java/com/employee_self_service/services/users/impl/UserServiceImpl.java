package com.employee_self_service.services.users.impl;

import com.employee_self_service.audits.ApplicationAuditAware;
import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.dtos.users.card.CardDataDTO;
import com.employee_self_service.dtos.users.card.DashboardCardDTO;
import com.employee_self_service.entities.users.User;
import com.employee_self_service.exceptions.client.UserNotFoundException;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.mappers.client.UserMapper;
import com.employee_self_service.repositories.users.UserRepository;
import com.employee_self_service.security.dtos.RegisterDTO;
import com.employee_self_service.services.users.UserService;
import com.employee_self_service.services.hr.HolidayService;
import com.employee_self_service.utils.EssConstants;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationAuditAware applicationAuditAware;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, ApplicationAuditAware applicationAuditAware, UserMapper userMapper, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationAuditAware = applicationAuditAware;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

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
        user.setCreatedBy(applicationAuditAware.getCurrentAuditor().orElse("unknown"));
        user.setLastUpdatedBy(applicationAuditAware.getCurrentAuditor().orElse("unknown"));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
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
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (user.isAccountNonLocked()) {
            return;
        }
        user.setAccountNonLocked(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void enableUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (user.isAccountEnabled()) {
            return;
        }
        user.setAccountEnabled(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void disableUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (!user.isAccountEnabled()) {
            return;
        }
        user.setAccountEnabled(false);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void lockUserAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(EssConstants.UserError.USER_NOT_FOUND)
        );
        if (!user.isAccountNonLocked()) {
            return;
        }
        user.setAccountNonLocked(false);
        userRepository.saveAndFlush(user);
    }
}
