package com.jwtauthentication.services.impl;

import com.jwtauthentication.audits.ApplicationAuditAware;
import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.dtos.client.dashboard.CardDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.UserNotFoundException;
import com.jwtauthentication.exceptions.common.BaseException;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.repositories.client.UserRepository;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.services.client.UserService;
import com.jwtauthentication.services.hr.HolidayService;
import com.jwtauthentication.utils.EssConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationAuditAware applicationAuditAware;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final HolidayService holidayService;

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

    @Override
    public Map<String, CardDTO> getDashboardCards() {
        Map<String, CardDTO> cardDTOMap = new HashMap<>(3);
        CardDTO holidayCard = new CardDTO(
                "Yearly", String.valueOf(holidayService.getAllHolidays().size()),
                "Monthly", String.valueOf(holidayService.getAllHolidaysByMonthAndYear(LocalDate.now().getMonthValue(), LocalDate.now().getYear()).size())
        );
        cardDTOMap.put("holiday", holidayCard);

        CardDTO shiftCard = new CardDTO(
                "Type", "GS",
                "Duration", "7:30"
        );

        CardDTO leaveCard = new CardDTO(
                "Yearly", 3 +"/"+ 18,
                "Monthly", 2 +"/"+ 1.5
        );
        cardDTOMap.put("leave", leaveCard);
        return cardDTOMap;
    }
}
