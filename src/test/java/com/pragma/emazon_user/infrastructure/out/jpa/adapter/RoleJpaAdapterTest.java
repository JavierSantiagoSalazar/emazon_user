package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.exception.role.RoleDoesNotExistException;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void givenRoleIdExists_whenGetRoleByIdIsCalled_thenReturnRole() {

        when(roleRepository.findById(defaultRole.getRoleId())).thenReturn(Optional.of(defaultRoleEntity));
        when(roleEntityMapper.roDomain(defaultRoleEntity)).thenReturn(defaultRole);

        Role result = roleJpaAdapter.getRoleById(defaultRole.getRoleId());

        verify(roleRepository, times(1)).findById(defaultRole.getRoleId());
        verify(roleEntityMapper, times(1)).roDomain(defaultRoleEntity);
        assertEquals(defaultRole, result);
    }

    @Test
    void givenRoleIdDoesNotExist_whenGetRoleByIdIsCalled_thenThrowException() {

        when(roleRepository.findById(defaultRole.getRoleId())).thenReturn(Optional.empty());

        Integer roleId = defaultRole.getRoleId();
        assertThrows(RoleDoesNotExistException.class, () -> roleJpaAdapter.getRoleById(roleId));

        verify(roleRepository, times(1)).findById(defaultRole.getRoleId());
        verify(roleEntityMapper, never()).roDomain(any(RoleEntity.class));
    }

}
