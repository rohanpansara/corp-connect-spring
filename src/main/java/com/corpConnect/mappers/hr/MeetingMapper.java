package com.corpConnect.mappers.hr;

import com.corpConnect.dtos.hr.MeetingDTO;
import com.corpConnect.entities.hr.Meeting;
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
import java.util.Objects;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class MeetingMapper {

    private static final Logger logger = LoggerFactory.getLogger(MeetingMapper.class);

    @Autowired
    private UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract Meeting toEntity(MeetingDTO meetingDTO);

    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "lastUpdatedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract MeetingDTO toDTO(Meeting meeting);

    public abstract List<Meeting> toEntityList(List<MeetingDTO> meetingDTOList);

    public abstract List<MeetingDTO> toDTOList(List<Meeting> meetingList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDTO(MeetingDTO meetingDTO, @MappingTarget Meeting meeting);

    @AfterMapping
    protected void setOrganizerAndMeetingRoom(MeetingDTO meetingDTO, @MappingTarget Meeting meeting) {

        if ((meetingDTO.getOrganizer() == null || meetingDTO.getOrganizer().getId() == null)
                && (meetingDTO.getMeetingRoom() == null || meetingDTO.getMeetingRoom().getId() == null)) {
            logger.warn(LogConstants.getEntityMapperLogPrefix("Meeting", "organizer or organizer's Id is null"));
            logger.warn(LogConstants.getEntityMapperLogPrefix("Meeting", "meeting room or meeting room's Id is null"));
            return;
        }

        assert Objects.requireNonNull(meetingDTO.getOrganizer()).getId() != null;

        User organizer = userRepository.findById(meetingDTO.getOrganizer().getId())
                .orElseThrow(() -> {
                    logger.error(LogConstants.getNotFoundMessage("User", "set", "ID", meetingDTO.getOrganizer().getId(), "inside after mapping"));
                    return new UserNotFoundException(MessageConstants.UserError.USER_NOT_FOUND);
                });

        meeting.setOrganizer(organizer);
    }

    @AfterMapping
    protected void dateFormatting(Meeting meeting, @MappingTarget MeetingDTO meetingDTO) throws BaseException {
        meetingDTO.setCreatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(meeting.getCreatedDate()));
        meetingDTO.setLastUpdatedDate(CustomDateTimeFormatter.getFormatedDateTimeByIntensity(meeting.getLastUpdatedDate()));
    }

}
