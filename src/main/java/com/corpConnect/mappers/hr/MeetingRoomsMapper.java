package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.MeetingRoomsDTO;
import com.corpConnect.entities.hr.MeetingRooms;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class MeetingRoomsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract MeetingRooms toEntity(MeetingRoomsDTO meetingRoomsDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract MeetingRoomsDTO toDTO(MeetingRooms meetingRooms);

    public abstract List<MeetingRooms> toEntityList(List<MeetingRoomsDTO> meetingRoomsDTOList);

    public abstract List<MeetingRoomsDTO> toDTOList(List<MeetingRooms> meetingRoomsList);

    @AfterMapping
    protected void dateFormatting(MeetingRooms meetingRooms, @MappingTarget MeetingRoomsDTO meetingRoomsDTO) throws BaseException {
        meetingRoomsDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(meetingRooms.getCreatedDate()));
        meetingRoomsDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(meetingRooms.getLastUpdatedDate()));
        if(meetingRooms.getCapacity()==null){
            meetingRoomsDTO.setCapacity(0);
        }
    }

}
