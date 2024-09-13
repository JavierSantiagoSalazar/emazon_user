package com.pragma.emazon_user.domain.usecase;

import com.pragma.emazon_user.domain.exception.user.UserInvalidEmailFormatException;
import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;
import com.pragma.emazon_user.domain.spi.AuthenticationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationUseCaseTest {

    @Mock
    private AuthenticationPersistencePort authenticationPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    private Login validLogin;
    private Login invalidEmailLogin;

    @BeforeEach
    public void setUp() {
        validLogin = new Login("javier.perez@example.com", "password123");
        invalidEmailLogin = new Login("invalid-email", "password123");
    }

    @Test
    void givenValidLoginRequest_whenLoginUserIsCalled_thenAuthenticationIsSuccessful() {
        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .username("javier.perez@example.com")
                .message("Login successful")
                .accessToken("token")
                .status(true)
                .build();

        when(authenticationPersistencePort.loginUser(validLogin)).thenReturn(expectedResponse);

        AuthenticationResponse response = authenticationUseCase.loginUser(validLogin);

        assertEquals(expectedResponse, response);
        verify(authenticationPersistencePort, times(1)).loginUser(validLogin);
    }

    @Test
    void givenInvalidEmailFormat_whenLoginUserIsCalled_thenThrowsUserInvalidEmailFormatException() {
        assertThrows(UserInvalidEmailFormatException.class, () -> authenticationUseCase.loginUser(invalidEmailLogin));

        verify(authenticationPersistencePort, never()).loginUser(invalidEmailLogin);
    }
}
