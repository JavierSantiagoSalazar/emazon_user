package com.pragma.emazon_user.infrastructure.out.jpa.repository;

import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRoleName(RoleEnum roleName);

}
