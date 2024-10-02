package com.employee_self_service.mappers.client;

import com.employee_self_service.dtos.users.UserDTO;
import com.employee_self_service.entities.users.Users;
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

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    @Lazy
    private UserService userService;

    @Named("toDTO")
    public abstract UserDTO toDTO(Users users);

    @Named("toEntity")
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract Users toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    public abstract Users toEntityFromRegisterDTO(RegisterDTO registerDTO);

    public abstract List<Users> toEntityList(List<UserDTO> userDTOList);
    public abstract List<UserDTO> toDTOList(List<Users> usersList);

    @BeforeMapping
    protected void validatePassword(RegisterDTO registerDTO, @MappingTarget Users users) throws BaseException {
        if (!isEmailExists(registerDTO.getEmail())) {
            throw new LoginFailedException(EssConstants.UserError.EMAIL_EXISTS);
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BaseException(EssConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    private boolean isEmailExists(String email) {
        Users users = userService.getUserByEmail(email);
        return ObjectUtils.isEmpty(users);
    }

    @AfterMapping
    protected void stringFormatting(Users users, @MappingTarget UserDTO userDTO) {
        userDTO.setRoles(users.getRoles().getLabel());
        userDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(users.getCreatedDate()));
        userDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(users.getLastUpdatedDate()));
    }

}
