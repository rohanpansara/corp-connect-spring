package com.corpConnect.repositories.users;

import com.corpConnect.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Users findUserById(Long userId);
    List<Users> findByIsAccountNonExpired(boolean isAccountNonExpired);
}
