package com.corpConnect.repositories.user;

import com.corpConnect.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findUserById(Long userId);
    List<User> findByIsAccountNonExpired(boolean isAccountNonExpired);

    List<User> findByIsDeleted(boolean isDeleted);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.id = :userId")
    void setIsDeletedTrueForUserByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.id IN :userIdList")
    void setIsDeletedTrueForUserByUserIdList(@Param("userIdList") List<Long> userIdList);
}
