package com.pragma.emazon_user.domain.spi;


import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEnum;

import java.util.Optional;

public interface RolePersistencePort {

    Optional<Role> getRoleByRoleName(RoleEnum roleEnum);

}
