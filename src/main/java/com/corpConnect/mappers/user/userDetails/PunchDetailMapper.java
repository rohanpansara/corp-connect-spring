package com.corpConnect.mappers.user.userDetails;

import com.corpConnect.dtos.user.userDetails.PunchDetailDTO;
import com.corpConnect.entities.user.userDetails.PunchDetail;
import com.corpConnect.services.user.UserService;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class PunchDetailMapper {

    @Autowired
    private UserService userService;

    @Named("toEntity")
    @Mapping(target = "allowed", ignore = true)
    public abstract PunchDetail toEntity(PunchDetailDTO punchDetailDTO);

    @Named("toDTO")
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract PunchDetailDTO toDTO(PunchDetail punchDetail);

    public abstract List<PunchDetail> toEntityList(List<PunchDetailDTO> punchDetailDTOList);
    public abstract List<PunchDetailDTO> toDTOList(List<PunchDetail> punchDetailList);

    @AfterMapping
    protected void stringFormatting(PunchDetail punchDetail, @MappingTarget PunchDetailDTO punchDetailDTO){
        punchDetailDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(punchDetail.getCreatedDate()));
        punchDetailDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(punchDetail.getLastUpdatedDate()));
        punchDetailDTO.setCreatedBy(userService.getUsernameByUserId(punchDetail.getUser().getId()));
        punchDetailDTO.setLastUpdatedBy(userService.getUsernameByUserId(punchDetail.getUser().getId()));
    }

}
