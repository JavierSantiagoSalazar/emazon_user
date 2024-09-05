package com.pragma.emazon_user.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon_user.application.dto.UserRequest;
import com.pragma.emazon_user.application.handler.UserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserHandler userHandler;

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
    void givenValidUserRequest_whenCreateWarehouseAssistant_thenReturns201() throws Exception {

        doNothing().when(userHandler).createWarehouseAssistant(userRequest);

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    void givenInvalidUserName_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserName("");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[User name cannot be empty]"));
    }

    @Test
    void givenUserNameExceedsMaxLength_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserName("A".repeat(151));

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[User name cannot exceed 150 characters]"));
    }

    @Test
    void givenInvalidUserLastName_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserLastName("");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[User last name cannot be empty]"));
    }

    @Test
    void givenUserLastNameExceedsMaxLength_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserLastName("B".repeat(151));

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[User last name cannot exceed 150 characters]"));
    }

    @Test
    void givenInvalidUserDocument_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserDocument("");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[User document cannot be empty]"));
    }

    @Test
    void givenUserDocumentExceedsMaxLength_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserDocument("12345678901");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[User document cannot exceed 10 characters]"));
    }

    @Test
    void givenInvalidUserPhonePattern_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserPhone("invalidPhone");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Phone number must contain only numbers and may start with + symbol]"));
    }

    @Test
    void givenUserPhoneExceedsMaxLength_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserPhone("+12345678901234");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Phone number cannot exceed 13 characters]"));
    }

    @Test
    void givenInvalidUserBirthdatePattern_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserBirthdate("01-01-1990");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Birthdate must be in yyyy/mm/dd format]"));
    }

    @Test
    void givenInvalidUserEmailPattern_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserEmail("invalidEmail");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Invalid email]"));
    }

    @Test
    void givenInvalidUserPasswordPattern_whenCreateWarehouseAssistant_thenReturns400() throws Exception {

        userRequest.setUserPassword(" ");

        mockMvc.perform(post("/user/warehouseAssistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(
                        "$.message",
                        containsString("Password cannot be empty")
                ))
                .andExpect(jsonPath(
                        "$.message",
                        containsString("Password cannot contain white spaces")
                ));
    }

}

