package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.application.dto.auth.LoginRequest;
import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.infrastructure.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationAdapterTest {

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDetailsServiceAdapter userDetailService;

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private AuthenticationAdapter authenticationAdapter;

    private LoginRequest validLoginRequest;
    private LoginRequest invalidLoginRequest;
    private Login validLogin;
    private Login invalidLogin;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        validLoginRequest = new LoginRequest("javier.perez@example.com", "password123");
        invalidLoginRequest = new LoginRequest("javier.perez@example.com", "wrongPassword");
        validLogin = new Login("javier.perez@example.com", "password123");
        invalidLogin = new Login("javier.perez@example.com", "wrongPassword");

        userDetails = new org.springframework.security.core.userdetails.User(
                "javier.perez@example.com",
                "encodedPassword123",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Test
    void givenValidLoginRequest_whenLoginUser_thenReturnAuthenticationResponse() {

        when(userDetailService.loadUserByUsername(validLoginRequest.getUserEmail())).thenReturn(userDetails);
        when(passwordEncoder.matches(validLoginRequest.getUserPassword(), userDetails.getPassword())).thenReturn(true);
        when(userPersistencePort.getUserIdByEmail(validLoginRequest.getUserEmail())).thenReturn(Optional.of(1));
        when(jwtUtils.createToken(any(Authentication.class), eq(1))).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationAdapter.loginUser(validLogin);

        assertEquals(validLoginRequest.getUserEmail(), response.getUsername());
        assertEquals(Constants.LOGIN_SUCCESSFULLY, response.getMessage());
        assertEquals("jwtToken", response.getAccessToken());
        assertTrue(response.getStatus());

        verify(userDetailService, times(1))
                .loadUserByUsername(validLoginRequest.getUserEmail());
        verify(passwordEncoder, times(1))
                .matches(validLoginRequest.getUserPassword(), userDetails.getPassword());
        verify(userPersistencePort, times(1))
                .getUserIdByEmail(validLoginRequest.getUserEmail());
        verify(jwtUtils, times(1))
                .createToken(any(Authentication.class), eq(1));
    }

    @Test
    void givenInvalidPassword_whenLoginUser_thenThrowBadCredentialsException() {

        when(userDetailService.loadUserByUsername(validLoginRequest.getUserEmail())).thenReturn(userDetails);
        when(passwordEncoder.matches(invalidLoginRequest.getUserPassword(), userDetails.getPassword())).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> authenticationAdapter.loginUser(invalidLogin));

        verify(userDetailService, times(1))
                .loadUserByUsername(invalidLoginRequest.getUserEmail());
        verify(passwordEncoder, times(1))
                .matches(invalidLoginRequest.getUserPassword(), userDetails.getPassword());
        verify(jwtUtils, never()).createToken(any(Authentication.class), eq(1));
    }

    @Test
    void givenNonexistentUser_whenAuthenticate_thenThrowBadCredentialsException() {

        when(userDetailService.loadUserByUsername(validLoginRequest.getUserEmail())).thenReturn(null);

        String email = validLoginRequest.getUserEmail();
        String password = validLoginRequest.getUserPassword();

        assertThrows(BadCredentialsException.class, () ->
                authenticationAdapter.authenticate(email, password)
        );

        verify(userDetailService, times(1)).loadUserByUsername(validLoginRequest.getUserEmail());
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }
}
