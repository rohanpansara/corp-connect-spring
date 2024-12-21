package com.corpConnect.mappers.user.userDetails;

import com.corpConnect.dtos.user.userDetails.PunchDetailDTO;
import com.corpConnect.entities.user.userDetails.PunchDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class PunchDetailMapper {

    @Named("toDTO")
    @Mapping(target = "allowed", ignore = true)
    public abstract PunchDetail toDTO(PunchDetailDTO punchDetailDTO);

}
