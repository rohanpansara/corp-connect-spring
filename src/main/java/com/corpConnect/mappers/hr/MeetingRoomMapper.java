package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.MeetingRoom;
import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.client.UserNotFoundException;
import com.corpConnect.exceptions.common.BaseException;
import com.corpConnect.repositories.user.UserRepository;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class MeetingRoomMapper {

    private static final Logger logger = LoggerFactory.getLogger(MeetingRoomMapper.class);

    @Autowired
    private UserRepository userRepository;

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(MeetingRoomDTO meetingRoomDTO, @MappingTarget MeetingRoom meetingRoom);

    @AfterMapping
    protected void setPointOfContact(MeetingRoomDTO meetingRoomDTO, @MappingTarget MeetingRoom meetingRoom) {

        if (meetingRoomDTO.getPointOfContactId() == null || meetingRoomDTO.getPointOfContactId().getId() == null) {
            logger.warn(LogConstants.getEntityMapperLogPrefix("Meeting Room", "pointOfContact or pointOfContact's Id is null"));
            return;
        }

        User pointOfContact = userRepository.findById(meetingRoomDTO.getPointOfContactId().getId())
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("User", "set", "ID", meetingRoomDTO.getPointOfContactId().getId(), "inside after mapping"));
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                });
        meetingRoom.setPointOfContact(pointOfContact);
    }

    @AfterMapping
    protected void dateFormatting(MeetingRoom meetingRoom, @MappingTarget MeetingRoomDTO meetingRoomDTO) throws BaseException {
        meetingRoomDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(meetingRoom.getCreatedDate()));
        meetingRoomDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(meetingRoom.getLastUpdatedDate()));
        if(meetingRoom.getCapacity()==null){
            meetingRoomDTO.setCapacity(0);
        }
    }

}
