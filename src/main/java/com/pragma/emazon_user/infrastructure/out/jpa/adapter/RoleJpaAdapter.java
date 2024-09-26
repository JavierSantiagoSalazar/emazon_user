package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.spi.RolePersistencePort;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEnum;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements RolePersistencePort {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> getRoleByRoleName(RoleEnum roleEnum) {
        return roleRepository.findByRoleName(roleEnum)
                .map(roleEntityMapper::toDomain);
    }

}
