package com.pragma.emazon_user.application.handler.auth;

import com.pragma.emazon_user.application.dto.auth.AuthenticationResponseDto;
import com.pragma.emazon_user.application.dto.auth.LoginRequest;
import com.pragma.emazon_user.application.mappers.AuthenticationResponseDtoMapper;
import com.pragma.emazon_user.application.mappers.LoginRequestMapper;
import com.pragma.emazon_user.domain.api.AuthenticationServicePort;
import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationHandlerImplTest {

    @InjectMocks
    private AuthenticationHandlerImpl authenticationHandler;

    @Mock
    private AuthenticationServicePort authenticationServicePort;

    @Mock
    private LoginRequestMapper loginRequestMapper;

    @Mock
    private AuthenticationResponseDtoMapper authenticationResponseDtoMapper;

    private LoginRequest loginRequest;
    private Login loginDomain;
    private AuthenticationResponse authenticationResponse;
    private AuthenticationResponseDto authenticationResponseDto;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("user@example.com", "password123");
        loginDomain = new Login("user@example.com", "password123");
        authenticationResponse = new AuthenticationResponse("user@example.com",
                "logged" ,
                "JWT_TOKEN",
                true
        );
        authenticationResponseDto = new AuthenticationResponseDto(
                "user@example.com",
                "logged" ,
                "JWT_TOKEN",
                true
        );
    }

    @Test
    void givenValidLoginRequest_whenLoginUser_thenReturnAuthenticationResponseDto() {

        when(loginRequestMapper.toDomain(loginRequest)).thenReturn(loginDomain);
        when(authenticationServicePort.loginUser(loginDomain)).thenReturn(authenticationResponse);
        when(authenticationResponseDtoMapper.toResponse(authenticationResponse)).thenReturn(authenticationResponseDto);

        AuthenticationResponseDto result = authenticationHandler.loginUser(loginRequest);

        assertNotNull(result);
        assertEquals(authenticationResponseDto, result);

        verify(loginRequestMapper).toDomain(loginRequest);
        verify(authenticationServicePort).loginUser(loginDomain);
        verify(authenticationResponseDtoMapper).toResponse(authenticationResponse);
    }

    @Test
    void givenLoginRequest_whenLoginUserFails_thenThrowException() {

        when(loginRequestMapper.toDomain(loginRequest)).thenReturn(loginDomain);
        when(authenticationServicePort.loginUser(loginDomain))
                .thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(RuntimeException.class, () -> authenticationHandler.loginUser(loginRequest));

        verify(loginRequestMapper).toDomain(loginRequest);
        verify(authenticationServicePort).loginUser(loginDomain);
        verify(authenticationResponseDtoMapper, never()).toResponse(any());
    }
}

