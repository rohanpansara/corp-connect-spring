package com.employee_self_service.mappers.client;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.entities.users.User;
import com.employee_self_service.exceptions.client.LoginFailedException;
import com.employee_self_service.exceptions.common.BaseException;
import com.employee_self_service.security.dtos.RegisterDTO;
import com.employee_self_service.services.users.UserService;
import com.employee_self_service.utils.CustomDateTimeFormatter;
import com.employee_self_service.utils.EssConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    @Lazy
    private UserService userService;

    @Named("toDTO")
    public abstract UserDTO toDTO(User user);

    @Named("toEntity")
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    public abstract User toEntityFromRegisterDTO(RegisterDTO registerDTO);

    public abstract List<User> toEntityList(List<UserDTO> userDTOList);
    public abstract List<UserDTO> toDTOList(List<User> userList);

    @BeforeMapping
    protected void validatePassword(RegisterDTO registerDTO, @MappingTarget User user) throws BaseException {
        if (!isEmailExists(registerDTO.getEmail())) {
            throw new LoginFailedException(EssConstants.UserError.EMAIL_EXISTS);
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BaseException(EssConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    private boolean isEmailExists(String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return ObjectUtils.isEmpty(user);
    }

    @AfterMapping
    protected void stringFormatting(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setRoles(user.getRoles().getLabel());
        userDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(user.getCreatedDate()));
        userDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(user.getLastUpdatedDate()));
    }

}
