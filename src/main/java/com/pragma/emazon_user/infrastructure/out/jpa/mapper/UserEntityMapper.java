package com.pragma.emazon_user.infrastructure.out.jpa.mapper;

import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "userRole.roleUsers", ignore = true)
    UserEntity toEntity(User user);

}
