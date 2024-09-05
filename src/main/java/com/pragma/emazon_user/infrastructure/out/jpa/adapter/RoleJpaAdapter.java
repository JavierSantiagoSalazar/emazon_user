package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.exception.role.RoleDoesNotExistException;
import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.spi.RolePersistencePort;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements RolePersistencePort {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role getRoleById(Integer roleId) {

        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(
                () -> new RoleDoesNotExistException(roleId)
        );
        return roleEntityMapper.roDomain(roleEntity);
    }

}
