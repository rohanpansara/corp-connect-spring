package com.corpConnect.services.user;

import com.corpConnect.audits.ApplicationAuditAware;
import com.corpConnect.dtos.card.BaseCardDTO;
import com.corpConnect.dtos.card.dashboard.AttendanceAverageBaseCardDTO;
import com.corpConnect.dtos.card.dashboard.LeftSideCardsDTO;
import com.corpConnect.dtos.card.dashboard.MonthlyAttendanceCardDTO;
import com.corpConnect.dtos.card.dashboard.RightSideCardsDTO;
import com.corpConnect.dtos.common.PageDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.entities.common.filter.UserFilter;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.UserNotFoundException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.mappers.user.UserMapper;
import com.corpConnect.repositories.user.UserRepository;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.security.utils.UserRole;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public User getUserFromRegisterDTO(NewUserDTO newUserDTO) throws BaseException {
        return userMapper.toEntityFromNewUserDTO(newUserDTO);
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
            logger.info(LogConstants.getAuditorNotFoundMessage("User", user.getEmail(), "while creating"));
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
    public String getUsernameByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            return "unknown";
        }
        return userRepository.findUsernameByUserId(userId);
    }

    @Override
    public List<User> getUserByAccountExpiration(Boolean isExpired) {
        return userRepository.findByIsAccountNonExpired(isExpired);
    }

    @Override
    public List<User> getAllUsers(Boolean isDeleted) {
        if (isDeleted == null) {
            logger.info(LogConstants.getFoundAllMessage("User", "get", "without deleted check"));
            return userRepository.findAll();
        } else if (isDeleted) {
            return this.getAllDeletedUsers();
        } else {
            return this.getAllNonDeletedUsers();
        }
    }

    @Override
    public List<User> getAllNonDeletedUsers() {
        logger.info(LogConstants.getFoundAllMessage("User", "get", "deleted check-" + false));
        return userRepository.findByDeleted(false);
    }

    @Override
    public List<User> getAllDeletedUsers() {
        logger.info(LogConstants.getFoundAllMessage("User", "get", "deleted check-" + true));
        return userRepository.findByDeleted(true);
    }

    @Override
    public PageDTO<UserDTO> getFilteredUsers(UserFilter filter) {
        Specification<User> userSpecificationFromUserFilter = this.getUserSpecificationFromUserFilter(filter);
        Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getRowsPerPage());
        Page<User> userPage = userRepository.findAll(userSpecificationFromUserFilter, pageable);

        if (filter.getPageNumber() >= userPage.getTotalPages()) {
            throw new RuntimeException("Requested page number exceeds available pages");
        }

        return userMapper.toPageDTO(userPage);
    }

    private Specification<User> getUserSpecificationFromUserFilter(UserFilter filter) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getSearchedString() != null && !filter.getSearchedString().isEmpty()) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + filter.getSearchedString().toLowerCase() + "%");
                Predicate emailPredicate = cb.like(cb.lower(root.get("email")), "%" + filter.getSearchedString().toLowerCase() + "%");
                predicate = cb.and(predicate, cb.or(namePredicate, emailPredicate));
            }

            if (filter.getRole() != null && !filter.getRole().isEmpty()) {
                try {
                    UserRole roleEnum = UserRole.valueOf(filter.getRole());
                    predicate = cb.and(predicate, cb.equal(root.get("roles"), roleEnum));
                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid role filter value: {}", filter.getRole());
                }
            }

            if (filter.isAccountNonExpired()) {
                predicate = cb.and(predicate, cb.isTrue(root.get("isAccountNonExpired")));
            }

            if (filter.isAccountUnlocked()) {
                predicate = cb.and(predicate, cb.isTrue(root.get("isAccountNonLocked")));
            }

            if (filter.isAccountEnabled()) {
                predicate = cb.and(predicate, cb.isTrue(root.get("isAccountEnabled")));
            }

            return predicate;
        };
    }

    @Override
    public User updateUserByUserIdAndUserDTO(Long userId, UserDTO userDTO) {
        User userToBeUpdated = userRepository.findById(userId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to update user with id: {}", userId);
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                }
        );

        userMapper.updateEntityFromDTO(userDTO, userToBeUpdated);
        return userRepository.save(userToBeUpdated);
    }

    @Transactional
    @Override
    public void deleteUserByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
        }
        userRepository.setIsDeletedTrueForUserByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteUsersByUserIdList(List<Long> userIdList) {
        if (!userIdList.isEmpty()) {
            userRepository.setIsDeletedTrueForUserByUserIdList(userIdList);
        }
    }

    @Override
    public LeftSideCardsDTO getLeftSideCards() {
        LeftSideCardsDTO dashboardCard = new LeftSideCardsDTO();
        dashboardCard.setLeaveDetailsCard(new BaseCardDTO(
                "Leaves Available",
                "12",
                "3 leaves taken last month"
        ));
        dashboardCard.setShiftDetailsCard(new BaseCardDTO(
                "Shift Timings",
                "7h 30m",
                "2 late-ins this month"
        ));
        dashboardCard.setMonthlyAttendanceCard(new MonthlyAttendanceCardDTO(
                "Monthly Attendance",
                "5",
                "No absent days or leaves this month",
                "21",
                false
        ));
        return dashboardCard;
    }

    @Override
    public RightSideCardsDTO getRightSideCards() {
        RightSideCardsDTO rightSideCardsDTO = new RightSideCardsDTO();
        rightSideCardsDTO.setTodayAttendanceCard(new AttendanceAverageBaseCardDTO(
                "Average Hours",
                "5.25",
                "On time",
                8,
                2.75,
                true
        ));
        rightSideCardsDTO.setWeeklyAttendanceCard(new AttendanceAverageBaseCardDTO(
                "Average Hours",
                "41",
                "2 late ins",
                40,
                -1d,
                false
        ));
        return rightSideCardsDTO;
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
