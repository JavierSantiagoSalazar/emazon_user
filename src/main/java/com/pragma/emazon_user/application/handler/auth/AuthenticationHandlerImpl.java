package com.pragma.emazon_user.application.handler.auth;

import com.pragma.emazon_user.application.dto.auth.AuthenticationResponseDto;
import com.pragma.emazon_user.application.dto.auth.LoginRequest;
import com.pragma.emazon_user.application.mappers.AuthenticationResponseDtoMapper;
import com.pragma.emazon_user.application.mappers.LoginRequestMapper;
import com.pragma.emazon_user.domain.api.AuthenticationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationHandlerImpl implements AuthenticationHandler {

    private final AuthenticationServicePort authenticationServicePort;

    private final LoginRequestMapper loginRequestMapper;
    private final AuthenticationResponseDtoMapper authenticationResponseDtoMapper;

    @Override
    public AuthenticationResponseDto loginUser(LoginRequest loginRequest) {
        return authenticationResponseDtoMapper.toResponse(
                authenticationServicePort.loginUser(
                        loginRequestMapper.toDomain(loginRequest)
                )
        );
    }

}
