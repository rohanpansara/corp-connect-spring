package com.corpConnect.repositories.user.userDetails;

import com.corpConnect.entities.user.userDetails.ExperienceDetail;
import com.corpConnect.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceDetailRepository extends JpaRepository<ExperienceDetail, Long> {

    ExperienceDetail findByUser(User user);
    ExperienceDetail findByUserId(Long userId);

    List<User> findByPreviousCompanyContaining(String previousCompanyName);
    List<User> findByPreviousCompanyLocationContaining(String previousCompanyLocation);
    List<User> findByPreviousManagerContaining(String previousManagerName);

    List<User> findByPreviousJobTitleContaining(String previousJobTitle);
    List<User> findByTechnologiesWorkedOnContaining(String technologyName);

    List<User> findByExperienceYears(Integer experienceYears);
    List<User> findByExperienceYearsGreaterThanEqual(Integer experienceYears);
    List<User> findByExperienceYearsLessThanEqual(Integer experienceYears);

}
