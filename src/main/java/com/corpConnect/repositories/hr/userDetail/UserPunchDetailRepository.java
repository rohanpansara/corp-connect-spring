package com.corpConnect.repositories.hr.userDetail;

import com.corpConnect.entities.user.User;
import com.corpConnect.entities.user.user_details.PunchDetails;
import com.corpConnect.enumerations.PunchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPunchDetailRepository extends JpaRepository<PunchDetails, Long> {

    List<PunchDetails> findByUser(User user);
    List<PunchDetails> findByUserId(Long userId);

    List<PunchDetails> findByPunchType(PunchType punchType);
    List<PunchDetails> findByIsAllowed(boolean isAllowed);
    List<PunchDetails> findByLocationContaining(String location);

}
