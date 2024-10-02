package com.employee_self_service.repositories.hr.userDetails;

import com.employee_self_service.entities.hr.userDetails.UserExperienceDetails;
import com.employee_self_service.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExperienceDetailsRepository extends JpaRepository<UserExperienceDetails, Long> {

    UserExperienceDetails findByUsers(Users users);

    List<Users> findByPreviousCompanyContaining(String previousCompanyName);
    List<Users> findByPreviousCompanyLocationContaining(String previousCompanyLocation);
    List<Users> findByPreviousManagerContaining(String previousManagerName);

    List<Users> findByPreviousJobTitleContaining(String previousJobTitle);
    List<Users> findByTechnologiesWorkedOnContaining(String technologyName);

    List<Users> findByExperienceYears(Integer experienceYears);
    List<Users> findByExperienceYearsGreaterThanEqual(Integer experienceYears);
    List<Users> findByExperienceYearsLessThanEqual(Integer experienceYears);

}
