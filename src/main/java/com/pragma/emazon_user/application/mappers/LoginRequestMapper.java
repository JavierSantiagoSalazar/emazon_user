package com.pragma.emazon_user.application.mappers;

import com.pragma.emazon_user.application.dto.auth.LoginRequest;
import com.pragma.emazon_user.domain.model.Login;
import com.pragma.emazon_user.domain.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LoginRequestMapper {

    Login toDomain(LoginRequest loginRequest);

}
