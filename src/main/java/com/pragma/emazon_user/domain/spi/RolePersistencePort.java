package com.pragma.emazon_user.domain.spi;


import com.pragma.emazon_user.domain.model.Role;

public interface RolePersistencePort {

    Role getRoleById(Integer roleId);

}
