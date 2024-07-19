package com.jwtauthentication.mappers.client;

import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.security.dtos.RegisterDTO;
import com.jwtauthentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.coyote.BadRequestException;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    private final UserService userService;

    public abstract UserDTO toDTO(User user);

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
    @Mapping(target = "credentialsNonExpired", defaultValue = "false")
    @Mapping(target = "accountNonLocked", defaultValue = "false")
    @Mapping(target = "accountNonExpired", defaultValue = "false")
    @Mapping(target = "accountEnabled", defaultValue = "false")
    public abstract User toEntityFromRegisterDTO(RegisterDTO registerDTO);

    public abstract List<User> toEntityList(List<UserDTO> userDTOList);
    public abstract List<UserDTO> toDTOList(List<User> userList);

    @BeforeMapping
    protected void validatePassword(RegisterDTO registerDTO, @MappingTarget User user) throws BadRequestException {

        if (!isEmailExists(registerDTO.getEmail())) {
            throw new BadRequestException(Ess.User.EMAIL_EXISTS);
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BadRequestException(TrufluxConstants.User.PASSWORD_MISMATCH);
        }
    }

    private boolean isEmailExists(String email) {
        Optional<User> user = userService.getByEmail(email);
        return ObjectUtils.isEmpty(user);
    }

}
