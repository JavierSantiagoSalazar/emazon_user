package com.pragma.emazon_user.application.mappers;

import com.pragma.emazon_user.application.dto.UserRequest;
import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.domain.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = Constants.SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserRequestMapper {

    @Mapping(target = "userBirthdate",
            expression = "java(convertToLocalDate(userRequest.getUserBirthdate()))"
    )
    User toDomain(UserRequest userRequest);

    default LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
