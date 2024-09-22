package com.employee_self_service.repositories.hr.userDetails;

import com.employee_self_service.entities.hr.userDetails.UserExperienceDetails;
import com.employee_self_service.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExperienceDetailsRepository extends JpaRepository<UserExperienceDetails, Long> {

    UserExperienceDetails findByUser(User user);

    List<User> findByPreviousCompanyContaining(String previousCompanyName);
    List<User> findByPreviousCompanyLocationContaining(String previousCompanyLocation);
    List<User> findByPreviousManagerContaining(String previousManagerName);

    List<User> findByPreviousJobTitleContaining(String previousJobTitle);
    List<User> findByTechnologiesWorkedOnContaining(String technologyName);

    List<User> findByExperienceYears(Integer experienceYears);
    List<User> findByExperienceYearsGreaterThanEqual(Integer experienceYears);
    List<User> findByExperienceYearsLessThanEqual(Integer experienceYears);

}
