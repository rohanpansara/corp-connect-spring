package com.jwtauthentication.repositories.hr;

import com.jwtauthentication.entities.hr.UserPersonalDetails;
import com.jwtauthentication.enumerations.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Long> {
    UserPersonalDetails findByUserId(Long userId);
    UserPersonalDetails findByGender(Gender gender);
}
