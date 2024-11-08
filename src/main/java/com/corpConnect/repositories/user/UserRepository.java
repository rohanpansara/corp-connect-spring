package com.corpConnect.repositories.user;

import com.corpConnect.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findUserById(Long userId);
    List<User> findByIsAccountNonExpired(boolean isAccountNonExpired);

    List<User> findByIsDeleted(boolean isDeleted);
}
