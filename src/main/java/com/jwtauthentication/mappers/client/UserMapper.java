package com.jwtauthentication.mappers.client;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.exceptions.client.LoginFailed;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.services.UserService;
import com.jwtauthentication.utils.EssConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.*;
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
    protected void validatePassword(RegisterDTO registerDTO, @MappingTarget User user) throws LoginFailed {

        if (!isEmailExists(registerDTO.getEmail())) {
            throw new LoginFailed(EssConstants.UserError.EMAIL_EXISTS);
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new RuntimeException(EssConstants.UserError.CONFIRM_PASSWORD_DID_NOT_MATCH);
        }
    }

    private boolean isEmailExists(String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return ObjectUtils.isEmpty(user);
    }

}
