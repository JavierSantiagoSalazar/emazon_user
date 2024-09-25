package com.pragma.emazon_user.infrastructure.out.jpa.repository;

import com.pragma.emazon_user.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserEmail(String userEmail);

    Optional<UserEntity> findByUserDocument(String userDocument);

    Optional<UserEntity> findByUserPhone(String userPhone);

    @Query("SELECT u.id FROM UserEntity u WHERE u.userEmail = :userEmail")
    Optional<Integer> findUserIdByEmail(@Param("userEmail") String userEmail);

}
