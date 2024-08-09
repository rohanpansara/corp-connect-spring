package com.jwtauthentication.repositories.hr;

import com.jwtauthentication.entities.hr.user.UserPersonalDetail;
import com.jwtauthentication.enumerations.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetail, Long> {
    UserPersonalDetail findByUserId(Long userId);
    UserPersonalDetail findByGender(Gender gender);
}
