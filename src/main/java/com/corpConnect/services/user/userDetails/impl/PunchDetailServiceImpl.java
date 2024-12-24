package com.corpConnect.services.user.userDetails.impl;

import com.corpConnect.dtos.user.userDetails.PunchDetailDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.entities.user.userDetails.PunchDetail;
import com.corpConnect.enumerations.PunchType;
import com.corpConnect.exceptions.client.RecordNotFoundException;
import com.corpConnect.mappers.user.userDetails.PunchDetailMapper;
import com.corpConnect.repositories.user.userDetails.PunchDetailRepository;
import com.corpConnect.services.user.UserService;
import com.corpConnect.services.user.userDetails.DepartmentDetailService;
import com.corpConnect.services.user.userDetails.PunchDetailService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.functions.MessageCreator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PunchDetailServiceImpl implements PunchDetailService {

    private static final Logger logger = LoggerFactory.getLogger(PunchDetailServiceImpl.class);

    private final UserService userService;
    private final DepartmentDetailService departmentDetailService;
    private final PunchDetailMapper punchDetailMapper;
    private final PunchDetailRepository punchDetailRepository;


    @Override
    public PunchDetail getEntity(PunchDetailDTO punchDetailDTO) {
        return punchDetailMapper.toEntity(punchDetailDTO);
    }

    @Override
    public PunchDetailDTO getDTO(PunchDetail punchDetail) {
        return punchDetailMapper.toDTO(punchDetail);
    }

    @Override
    public List<PunchDetail> getEntityList(List<PunchDetailDTO> punchDetailDTOList) {
        return punchDetailMapper.toEntityList(punchDetailDTOList);
    }

    @Override
    public List<PunchDetailDTO> getDTOList(List<PunchDetail> punchDetailsList) {
        return punchDetailMapper.toDTOList(punchDetailsList);
    }

    @Override
    public void createPunchDetail(PunchDetailDTO punchDetailDTO) {
        try {
            punchDetailRepository.save(this.getEntity(punchDetailDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Punch Detail", "DTO", punchDetailDTO, null));
        } catch (Exception e) {
            logger.error("Error -> ", e);
        }
    }

    @Override
    public void updatePunchDetail(Long oldPunchTitleId, PunchDetailDTO punchDetailDTO) {
        //TODO: for HR only
    }

    @Override
    public PunchDetail getPunchDetailById(Long punchDetailId) {
        return punchDetailRepository.findById(punchDetailId).orElseThrow(
                ()-> new RecordNotFoundException(MessageCreator.getNotFoundMessage("Punch detail"))
        );
    }

    @Override
    public void deletePunchDetail(PunchDetailDTO punchDetailDTO) {
        PunchDetail punchDetailToDelete = this.getEntity(punchDetailDTO);
        punchDetailRepository.delete(punchDetailToDelete);
        logger.info(LogConstants.getDeletedSuccessfullyMessage("Punch detail", "Permanent", "DTO", punchDetailDTO, null));
    }

    @Override
    public void deletePunchDetailById(Long punchTitleId) {
        punchDetailRepository.deleteById(punchTitleId);
        logger.info(LogConstants.getDeletedSuccessfullyMessage("Punch detail", "Permanent", "Id", punchTitleId, null));
    }

    @Override
    public List<PunchDetail> getPunchDetailByUserId(Long userId) {
        return punchDetailRepository.findByUserId(userId);
    }

    @Override
    public List<PunchDetail> getPunchDetailByDate(LocalDate date) {

        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = dateFrom.plusDays(1).minusSeconds(1);

        return punchDetailRepository.findByPunchTimeBetween(dateFrom, dateTo);
    }

    @Override
    public List<PunchDetail> getPunchDetailByUserIdAndDate(Long userId, LocalDate date) {

        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = dateFrom.plusDays(1).minusSeconds(1);

        return punchDetailRepository.findByUserIdInAndPunchTimeBetween(Collections.singletonList(userId), dateFrom, dateTo);
    }

    @Override
    public List<PunchDetail> getPunchDetailByDepartmentIdAndDate(Long departmentId, LocalDate date) {

        LocalDateTime dateFrom = date.atStartOfDay();
        LocalDateTime dateTo = dateFrom.plusDays(1).minusSeconds(1);

        List<Long> userIdListInTheDepartment = departmentDetailService.getUserIdsInTheDepartmentWithId(departmentId);
        logger.info(LogConstants.getFoundAllMessage("User Ids", "getting punch details for department", "for the date: "+date));

        return punchDetailRepository.findByUserIdInAndPunchTimeBetween(userIdListInTheDepartment, dateFrom, dateTo);
    }

    @Override
    public List<PunchDetail> getPunchDetailByUserIdAndDateAndPunchType(Long userId, LocalDate date, PunchType punchType) {
        return List.of();
    }

    @Override
    public List<User> getUsersWithForbiddenPunchesByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public List<User> getUsersWithForbiddenPunchesByDepartmentIdAndDate(Long departmentId, LocalDate date) {
        return List.of();
    }


}
