package com.pragma.emazon_user.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon_user.application.dto.UserRequest;
import com.pragma.emazon_user.application.dto.auth.AuthenticationResponseDto;
import com.pragma.emazon_user.application.dto.auth.LoginRequest;
import com.pragma.emazon_user.application.handler.auth.AuthenticationHandler;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.infrastructure.configuration.security.filter.JwtValidatorFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationHandler authenticationHandler;

    @MockBean
    private JwtValidatorFilter jwtValidatorFilter;

    @MockBean
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequest userRequest;

    @BeforeEach
    public void setUp() {

        userRequest = new UserRequest();
        userRequest.setUserName("John");
        userRequest.setUserLastName("Doe");
        userRequest.setUserDocument("1234567890");
        userRequest.setUserPhone("+123456789012");
        userRequest.setUserBirthdate("1990/01/01");
        userRequest.setUserEmail("john.doe@example.com");
        userRequest.setUserPassword("StrongPass123");
    }

    @Test
    void givenValidLoginRequest_whenLogin_thenReturns200() throws Exception {

        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "StrongPass123");

        AuthenticationResponseDto authenticationResponseDto = AuthenticationResponseDto.builder()
                .username(loginRequest.getUserEmail())
                .message(Constants.LOGIN_SUCCESSFULLY)
                .accessToken("mockedJwtToken")
                .status(true)
                .build();

        when(authenticationHandler.loginUser(any(LoginRequest.class)))
                .thenReturn(authenticationResponseDto);

        mockMvc.perform(post("/auth/log-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(loginRequest.getUserEmail()))
                .andExpect(jsonPath("$.message").value(Constants.LOGIN_SUCCESSFULLY))
                .andExpect(jsonPath("$.accessToken").value("mockedJwtToken"))
                .andExpect(jsonPath("$.status").value(true));

        verify(authenticationHandler, times(1)).loginUser(any(LoginRequest.class));
    }

    @Test
    void givenInvalidLoginRequest_whenLogin_thenReturns401() throws Exception {

        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "WrongPassword");

        when(authenticationHandler.loginUser(any(LoginRequest.class)))
                .thenThrow(new BadCredentialsException(Constants.BAD_CREDENTIALS));

        mockMvc.perform(post("/auth/log-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value(Constants.BAD_CREDENTIALS));

        verify(authenticationHandler, times(1)).loginUser(any(LoginRequest.class));
    }

}
