package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.model.Permission;
import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.PermissionEntity;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEnum;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RoleJpaAdapterTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    private Role defaultRole;
    private RoleEntity defaultRoleEntity;

    @BeforeEach
    public void setUp() {

        defaultRole = new Role(1,
                "aux_bodega",
                "description",
                Set.of(new Permission(1, "READ"))
        );
        defaultRoleEntity = new RoleEntity(1,
                RoleEnum.WAREHOUSE_ASSISTANT,
                "description",
                List.of(),
                Set.of(new PermissionEntity(1, "READ"))
        );
    }

    @Test
    void getRoleByRoleName_shouldReturnRoleWhenFound() {

        RoleEnum roleEnum = RoleEnum.WAREHOUSE_ASSISTANT;

        Mockito.when(roleRepository.findByRoleName(roleEnum)).thenReturn(Optional.of(defaultRoleEntity));
        Mockito.when(roleEntityMapper.toDomain(defaultRoleEntity)).thenReturn(defaultRole);

        Optional<Role> result = roleJpaAdapter.getRoleByRoleName(roleEnum);

        assertTrue(result.isPresent());
        assertEquals(defaultRole, result.get());
        Mockito.verify(roleRepository).findByRoleName(roleEnum);
        Mockito.verify(roleEntityMapper).toDomain(defaultRoleEntity);
    }

    @Test
    void getRoleByRoleName_shouldReturnEmptyWhenRoleNotFound() {

        RoleEnum roleEnum = RoleEnum.WAREHOUSE_ASSISTANT;

        Mockito.when(roleRepository.findByRoleName(roleEnum)).thenReturn(Optional.empty());

        Optional<Role> result = roleJpaAdapter.getRoleByRoleName(roleEnum);

        assertTrue(result.isEmpty());
        Mockito.verify(roleRepository).findByRoleName(roleEnum);
        Mockito.verify(roleEntityMapper, Mockito.never()).toDomain(Mockito.any(RoleEntity.class));
    }
}
