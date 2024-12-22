package com.corpConnect.repositories.user.userDetails;

import com.corpConnect.entities.user.User;
import com.corpConnect.entities.user.userDetails.PunchDetail;
import com.corpConnect.enumerations.PunchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PunchDetailRepository extends JpaRepository<PunchDetail, Long> {

    List<PunchDetail> findByUser(User user);
    List<PunchDetail> findByUserId(Long userId);

    List<PunchDetail> findByPunchType(PunchType punchType);
    List<PunchDetail> findByAllowed(boolean allowed);
    List<PunchDetail> findByLocationId(Long locationId);

}
