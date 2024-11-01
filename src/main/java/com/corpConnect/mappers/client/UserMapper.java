package com.corpConnect.mappers.client;

import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import com.corpConnect.utils.constants.MessageConstants;
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

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    @Lazy
    @Autowired
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
    public abstract User toEntityFromRegisterDTO(NewUserDTO newUserDTO);

    public abstract List<User> toEntityList(List<UserDTO> userDTOList);

    public abstract List<UserDTO> toDTOList(List<User> userList);

    @BeforeMapping
    protected void validatePassword(NewUserDTO newUserDTO, @MappingTarget User user) throws BaseException {
        if (!isEmailExists(newUserDTO.getEmail())) {
            throw new LoginFailedException(MessageConstants.UserError.EMAIL_EXISTS);
        }

        if (!newUserDTO.getPassword().equals(newUserDTO.getConfirmPassword())) {
            throw new BaseException(MessageConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    private boolean isEmailExists(String email) {
        User user = userService.getUserByEmail(email);
        return ObjectUtils.isEmpty(user);
    }

    @AfterMapping
    protected void stringFormatting(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setRoles(user.getRoles().getLabel());
        userDTO.setPermissions(user.getRoles().getPermissionStrings());
        userDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(user.getCreatedDate()));
        userDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(user.getLastUpdatedDate()));
    }

}
