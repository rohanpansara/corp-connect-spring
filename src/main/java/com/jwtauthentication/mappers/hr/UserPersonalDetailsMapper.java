package com.jwtauthentication.mappers.hr;

import com.jwtauthentication.dtos.hr.UserPersonalDetailsDTO;
import com.jwtauthentication.entities.hr.user.UserPersonalDetail;
import com.jwtauthentication.mappers.client.UserMapper;
import com.jwtauthentication.utils.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class UserPersonalDetailsMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "toDTO")
    public abstract UserPersonalDetailsDTO toDTO(UserPersonalDetail entity);

    @Mapping(target = "user", source = "user", qualifiedByName = "toEntity")
    public abstract UserPersonalDetail toEntity(UserPersonalDetailsDTO dto);

    public abstract List<UserPersonalDetailsDTO> toDTOList(List<UserPersonalDetail> userPersonalDetailList);
    public abstract List<UserPersonalDetail> toEntityList(List<UserPersonalDetailsDTO> userPersonalDetailsDTOList);

    @AfterMapping
    protected void dateFormatting(UserPersonalDetail userPersonalDetail, @MappingTarget UserPersonalDetailsDTO userPersonalDetailsDTO) {
        userPersonalDetailsDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(userPersonalDetail.getCreatedDate()));
        userPersonalDetailsDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(userPersonalDetail.getLastUpdatedDate()));
    }
}
