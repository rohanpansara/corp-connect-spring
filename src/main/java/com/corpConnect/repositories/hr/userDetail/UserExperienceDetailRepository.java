package com.corpConnect.repositories.hr.userDetail;

import com.corpConnect.entities.hr.userDetail.UserExperienceDetail;
import com.corpConnect.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExperienceDetailRepository extends JpaRepository<UserExperienceDetail, Long> {

    UserExperienceDetail findByUser(User user);

    List<User> findByPreviousCompanyContaining(String previousCompanyName);
    List<User> findByPreviousCompanyLocationContaining(String previousCompanyLocation);
    List<User> findByPreviousManagerContaining(String previousManagerName);

    List<User> findByPreviousJobTitleContaining(String previousJobTitle);
    List<User> findByTechnologiesWorkedOnContaining(String technologyName);

    List<User> findByExperienceYears(Integer experienceYears);
    List<User> findByExperienceYearsGreaterThanEqual(Integer experienceYears);
    List<User> findByExperienceYearsLessThanEqual(Integer experienceYears);

}