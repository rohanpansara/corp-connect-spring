package com.corpConnect.mappers.user;

import com.corpConnect.dtos.common.PageDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.NewUserDTO;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    @Lazy
    @Autowired
    private UserService userService;

    @Named("toDTO")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "isAccountEnabled", ignore = true)
    @Mapping(target = "isCredentialsNonExpired", ignore = true)
    @Mapping(target = "isAccountNonLocked", ignore = true)
    @Mapping(target = "isAccountNonExpired", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
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

    public PageDTO<UserDTO> toPageDTO(Page<User> userPage) {
        List<UserDTO> userDTOs = userPage.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageDTO<>(
                userDTOs,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isFirst(),
                userPage.isLast()
        );
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(UserDTO userDTO, @MappingTarget User user);

    @BeforeMapping
    protected void validatePassword(NewUserDTO newUserDTO, @MappingTarget User user) throws BaseException {
        if (!isEmailExists(newUserDTO.getEmail())) {
            throw new LoginFailedException(MessageConstants.UserError.EMAIL_EXISTS);
        }

//        if (!newUserDTO.getPassword().equals(newUserDTO.getConfirmPassword())) {
//            throw new BaseException(MessageConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
//        }
    }

    private boolean isEmailExists(String email) {
        User user = userService.getUserByEmail(email);
        return ObjectUtils.isEmpty(user);
    }

    @AfterMapping
    protected void stringFormatting(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setRoles(user.getRoles().getLabel());
        userDTO.setPermissions(user.getRoles().getPermissionStrings());
        userDTO.setIsAccountEnabled(String.valueOf(user.isAccountEnabled()));
        userDTO.setIsAccountNonExpired(String.valueOf(user.isAccountNonExpired()));
        userDTO.setIsAccountNonLocked(String.valueOf(user.isAccountNonLocked()));
        userDTO.setIsCredentialsNonExpired(String.valueOf(user.isCredentialsNonExpired()));
        userDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(user.getCreatedDate()));
        userDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(user.getLastUpdatedDate()));
        userDTO.setLastUpdatedBy(userService.getUsernameByUserId(user.getId()));
    }

}
