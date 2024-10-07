package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.MeetingRoom;
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
    public abstract MeetingRoom toEntity(MeetingRoomDTO meetingRoomDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract MeetingRoomDTO toDTO(MeetingRoom meetingRoom);

    public abstract List<MeetingRoom> toEntityList(List<MeetingRoomDTO> meetingRoomDTOList);

    public abstract List<MeetingRoomDTO> toDTOList(List<MeetingRoom> meetingRoomList);

    @AfterMapping
    protected void dateFormatting(MeetingRoom meetingRoom, @MappingTarget MeetingRoomDTO meetingRoomDTO) throws BaseException {
        meetingRoomDTO.setCreatedDate(CustomDateTimeFormatter.getLocalDateTimeString(meetingRoom.getCreatedDate()));
        meetingRoomDTO.setLastUpdatedDate(CustomDateTimeFormatter.getLocalDateTimeString(meetingRoom.getLastUpdatedDate()));
        if(meetingRoom.getCapacity()==null){
            meetingRoomDTO.setCapacity(0);
        }
    }

}
