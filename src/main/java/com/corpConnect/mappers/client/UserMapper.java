package com.corpConnect.mappers.client;

import com.corpConnect.dtos.users.UserDTO;
import com.corpConnect.entities.users.Users;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.security.dtos.RegisterDTO;
import com.corpConnect.services.users.UserService;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import com.corpConnect.utils.constants.CorpConnectConstants;
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
            throw new LoginFailedException(CorpConnectConstants.UserError.EMAIL_EXISTS);
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BaseException(CorpConnectConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
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
