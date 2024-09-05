package com.pragma.emazon_user.application.handler;

import com.pragma.emazon_user.application.dto.UserRequest;
import com.pragma.emazon_user.application.mappers.UserRequestMapper;
import com.pragma.emazon_user.domain.api.UserServicePort;
import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserHandlerImplTest {

    @Mock
    private UserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @InjectMocks
    private UserHandlerImpl userHandlerImpl;

    @Test
    void givenValidUserRequest_whenCreateUser_thenWarehouseAssistantIsSaved() {

        UserRequest userRequest = new UserRequest();
        userRequest.setUserName("  Javier  ");
        userRequest.setUserLastName("  Pérez  ");
        userRequest.setUserDocument("1234567890");
        userRequest.setUserPhone("+573001234567");
        userRequest.setUserBirthdate("1990/01/01");
        userRequest.setUserEmail("  javier.perez@example.com  ");
        userRequest.setUserPassword("  password123  ");

        User mappedUser = new User(
                null,
                "Javier",
                "Pérez",
                "1234567890",
                "+573001234567",
                LocalDate.of(1990, 1, 1),
                "javier.perez@example.com",
                "password123",
                new Role(1, "aux_bodega", "description")
        );

        when(userRequestMapper.toDomain(userRequest)).thenReturn(mappedUser);

        userHandlerImpl.createWarehouseAssistant(userRequest);

        verify(userRequestMapper, times(1)).toDomain(userRequest);
        verify(userServicePort, times(1)).saveWarehouseAssistant(mappedUser);
    }
}
