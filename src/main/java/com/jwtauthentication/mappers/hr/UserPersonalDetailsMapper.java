package com.jwtauthentication.mappers.hr;

import com.jwtauthentication.dtos.hr.UserPersonalDetailsDTO;
import com.jwtauthentication.entities.hr.UserPersonalDetail;
import com.jwtauthentication.mappers.client.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class UserPersonalDetailsMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "toDTO")
    public abstract UserPersonalDetailsDTO toDTO(UserPersonalDetail entity);

    @Mapping(target = "user", source = "user", qualifiedByName = "toEntity")
    public abstract UserPersonalDetail toEntity(UserPersonalDetailsDTO dto);

    public abstract List<UserPersonalDetailsDTO> toDTOList(List<UserPersonalDetail> userPersonalDetailList);
    public abstract List<UserPersonalDetail> toEntityList(List<UserPersonalDetailsDTO> userPersonalDetailsDTOList);
}
