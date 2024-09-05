package com.pragma.emazon_user.infrastructure.out.jpa.repository;

import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
