package com.jwtauthentication.mappers.hr;

import com.jwtauthentication.dtos.hr.UserPersonalDetailsDTO;
import com.jwtauthentication.entities.client.UserPersonalDetails;
import com.jwtauthentication.mappers.client.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class UserPersonalDetailsMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "toDTO")
    public abstract UserPersonalDetailsDTO toDTO(UserPersonalDetails entity);

    @Mapping(target = "user", source = "user", qualifiedByName = "toEntity")
    public abstract UserPersonalDetails toEntity(UserPersonalDetailsDTO dto);

    public abstract List<UserPersonalDetailsDTO> toDTOList(List<UserPersonalDetails> userPersonalDetailsList);
    public abstract List<UserPersonalDetails> toEntityList(List<UserPersonalDetailsDTO> userPersonalDetailsDTOList);
}
