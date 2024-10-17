package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.MeetingRoomDTO;
import com.corpConnect.entities.hr.MeetingRoom;
import com.corpConnect.exceptions.hr.JobTitleRelatedException;
import com.corpConnect.exceptions.hr.MeetingRoomRelatedException;
import com.corpConnect.mappers.hr.MeetingRoomMapper;
import com.corpConnect.repositories.hr.MeetingRoomRepository;
import com.corpConnect.services.hr.MeetingRoomService;
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
public class MeetingRoomServiceImpl implements MeetingRoomService {

    private static final Logger logger = LoggerFactory.getLogger(MeetingRoomServiceImpl.class);

    private final MeetingRoomMapper meetingRoomMapper;
    private final MeetingRoomRepository meetingRoomRepository;

    @Override
    public MeetingRoom getEntity(MeetingRoomDTO meetingRoomDTO) {
        return meetingRoomMapper.toEntity(meetingRoomDTO);
    }

    @Override
    public MeetingRoomDTO getDTO(MeetingRoom meetingRoom) {
        return meetingRoomMapper.toDTO(meetingRoom);
    }

    @Override
    public List<MeetingRoom> getEntityList(List<MeetingRoomDTO> meetingRoomDTOList) {
        return meetingRoomMapper.toEntityList(meetingRoomDTOList);
    }

    @Override
    public List<MeetingRoomDTO> getDTOList(List<MeetingRoom> meetingRoomList) {
        return meetingRoomMapper.toDTOList(meetingRoomList);
    }

    @Override
    public void createMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
        try {
            meetingRoomRepository.save(this.getEntity(meetingRoomDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Meeting Room", "DTO", meetingRoomDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Meeting Room", "Name", meetingRoomDTO.getName(), "while creating"));
            throw new RuntimeException(MessageConstants.MeetingRoom.MEETING_ROOM_CREATED);
        }
    }

    @Override
    public void updateMeetingRoom(Long oldMeetingRoomId, MeetingRoomDTO meetingRoomDTO) {
        MeetingRoom oldMeetingRoom = meetingRoomRepository.findById(oldMeetingRoomId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Meeting Room", "update", "ID", oldMeetingRoomId, null));
                    return new JobTitleRelatedException(MessageConstants.MeetingRoom.MEETING_ROOM_UPDATED);
                });

        try {
            meetingRoomMapper.updateEntityFromDTO(meetingRoomDTO, oldMeetingRoom);
            meetingRoomRepository.save(oldMeetingRoom);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Meeting Room", "DTO", meetingRoomDTO, "ID", oldMeetingRoomId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Meeting Room", "Name", meetingRoomDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.MeetingRoom.MEETING_ROOM_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteMeetingRoom(MeetingRoomDTO meetingRoomDTO, Boolean isPermanentDelete) {
        MeetingRoom meetingRoomToDelete = this.getEntity(meetingRoomDTO);
        try {
            if (isPermanentDelete) {
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Meeting Room", "Permanent", "DTO", meetingRoomDTO, null));
                meetingRoomRepository.delete(meetingRoomToDelete);
            } else {
                meetingRoomToDelete.setDeleted(true);
                meetingRoomRepository.save(meetingRoomToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Meeting Room", "Soft", "DTO", meetingRoomDTO, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Meeting Room", "DTO", meetingRoomDTO, null));
            throw new RuntimeException(MessageConstants.MeetingRoom.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteMeetingRoomById(Long meetingRoomId, Boolean isPermanentDelete) {
        MeetingRoom meetingRoomToDelete = meetingRoomRepository.findById(meetingRoomId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Meeting Room", "delete", "ID", meetingRoomId, null));
                    return new MeetingRoomRelatedException(MessageConstants.MeetingRoom.MEETING_ROOM_NOT_FOUND);
                });

        try {
            if (isPermanentDelete) {
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Meeting Room", "Permanent", "ID", meetingRoomId, null));
                meetingRoomRepository.delete(meetingRoomToDelete);
            } else {
                meetingRoomToDelete.setDeleted(true);
                meetingRoomRepository.save(meetingRoomToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Meeting Room", "Soft", "ID", meetingRoomId, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Meeting Room", "ID", meetingRoomId, null));
            throw new RuntimeException(MessageConstants.MeetingRoom.DataIntegrityViolation);
        }
    }

    @Override
    public List<MeetingRoom> getAllMeetingRooms(Boolean isDeleted) {
        if (isDeleted == null) {
            logger.info(LogConstants.getFoundAllMessage("Meeting Room", "get", "without deleted check"));
            return meetingRoomRepository.findAll();
        } else if (isDeleted) {
            return this.getAllDeletedMeetingRooms();
        } else {
            return this.getAllNonDeletedMeetingRooms();
        }
    }

    @Override
    public List<MeetingRoom> getAllNonDeletedMeetingRooms() {
        logger.info(LogConstants.getFoundAllMessage("Meeting Room", "get", "deleted check-" + false));
        return meetingRoomRepository.findByIsDeleted(false);
    }

    @Override
    public List<MeetingRoom> getAllDeletedMeetingRooms() {
        logger.info(LogConstants.getFoundAllMessage("Meeting Room", "get", "deleted check-" + true));
        return meetingRoomRepository.findByIsDeleted(true);
    }

    @Override
    public List<MeetingRoom> getMeetingRoomById(Long meetingRoomId) {
        logger.info(LogConstants.getFoundMessage("Meeting Room", "get", "ID", meetingRoomId, null));
        return Collections.singletonList(meetingRoomRepository.findMeetingRoomById(meetingRoomId));
    }

    @Override
    public List<MeetingRoom> getMeetingRoomByName(String meetingRoomName) {
        logger.info(LogConstants.getFoundAllMessage("Meeting Room", "get", "name containing-" + meetingRoomName));
        return Collections.singletonList(meetingRoomRepository.findByNameContaining(meetingRoomName));
    }
}
