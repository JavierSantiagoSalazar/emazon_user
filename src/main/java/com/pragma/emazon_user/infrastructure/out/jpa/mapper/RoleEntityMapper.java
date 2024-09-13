package com.pragma.emazon_user.infrastructure.out.jpa.mapper;

import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.SPRING_COMPONENT_MODEL)
public interface RoleEntityMapper {

    Role roDomain(RoleEntity roleEntity);

}
