package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.MeetingDTO;
import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.entities.hr.Meeting;
import com.corpConnect.enumerations.MeetingStatus;
import com.corpConnect.exceptions.client.RecordNotFoundException;
import com.corpConnect.exceptions.client.UserNotFoundException;
import com.corpConnect.exceptions.hr.JobTitleRelatedException;
import com.corpConnect.mappers.hr.MeetingMapper;
import com.corpConnect.repositories.hr.MeetingRepository;
import com.corpConnect.services.hr.MeetingService;
import com.corpConnect.services.user.UserServiceImpl;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;

    @Override
    public Meeting getEntity(MeetingDTO meetingDTO) {
        return meetingMapper.toEntity(meetingDTO);
    }

    @Override
    public MeetingDTO getDTO(Meeting meeting) {
        return meetingMapper.toDTO(meeting);
    }

    @Override
    public List<Meeting> getEntityList(List<MeetingDTO> meetingDTOList) {
        return meetingMapper.toEntityList(meetingDTOList);
    }

    @Override
    public List<MeetingDTO> getDTOList(List<Meeting> meetingList) {
        return meetingMapper.toDTOList(meetingList);
    }

    @Override
    public void createMeeting(MeetingDTO meetingDTO) {
        try {
            Meeting meeting = meetingMapper.toEntity(meetingDTO);
            meetingRepository.save(meeting);
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Meeting", "DTO", meetingDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Meeting", "Name", meetingDTO.getName(), "while creating"));
            throw new RuntimeException(MessageConstants.Meeting.MEETING_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateMeeting(Long meetingToUpdateId, MeetingDTO newMeetingDTO) {
        Meeting oldMeeting = meetingRepository.findById(meetingToUpdateId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Meeting", "update", "ID", meetingToUpdateId, null));
                    return new RecordNotFoundException(MessageConstants.Meeting.MEETING_NOT_FOUND);
                });

        try {
            meetingMapper.updateEntityFromDTO(newMeetingDTO, oldMeeting);
            meetingRepository.save(oldMeeting);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Meeting", "DTO", newMeetingDTO, "ID", meetingToUpdateId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Meeting", "Name", newMeetingDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.Meeting.MEETING_ALREADY_EXISTS);
        }
    }

    @Override
    public Meeting getMeetingById(Long meetingToGetId) {
        return null;
    }

    @Override
    public void deleteMeeting(MeetingRoomDTO meetingRoomDTO) {

    }

    @Override
    public void deleteMeetingById(Long meetingToDeleteId) {

    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Override
    public List<Meeting> getAllNonCompletedMeetings() {
        return meetingRepository.findAllByStatusIn(List.of(MeetingStatus.SCHEDULED, MeetingStatus.DELAYED, MeetingStatus.CANCELLED));
    }

    @Override
    public List<Meeting> getAllCompletedMeetings() {
        return meetingRepository.findAllByStatusIn(List.of(MeetingStatus.OVER));
    }

    @Override
    public List<Meeting> getMeetingsById(Long meetingId) {
        return Collections.singletonList(meetingRepository.findById(meetingId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to fetch meeting with id: {}", meetingId);
                    return new RuntimeException(MessageConstants.Meeting.MEETING_NOT_FOUND);
                }
        ));
    }

    @Override
    public List<Meeting> getMeetingsByName(String meetingName) {
        return List.of();
    }
}
