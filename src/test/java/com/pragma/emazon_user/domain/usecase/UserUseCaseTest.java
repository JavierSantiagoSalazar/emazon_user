package com.pragma.emazon_user.domain.usecase;

import com.pragma.emazon_user.domain.exception.user.UserAlreadyExistsException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidDocumentFormatException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidEmailFormatException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidPhoneFormatException;
import com.pragma.emazon_user.domain.exception.user.UserUnderageException;
import com.pragma.emazon_user.domain.model.Permission;
import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.domain.spi.PasswordEncoderPort;
import com.pragma.emazon_user.domain.spi.RolePersistencePort;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RolePersistencePort rolePersistencePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User defaultUser;

    @BeforeEach
    public void setUp() {

        defaultUser = new User(
                null,
                "Javier",
                "Pérez",
                "1234567890",
                "+573001234567",
                LocalDate.of(1990, 1, 1),
                "javier.perez@example.com",
                "password123",
                new Role(1,
                        "aux_bodega",
                        "roleDescription",
                        Set.of(new Permission(1, "READ"))
                )
        );
    }

    @Test
    void givenUserDoesNotExist_whenSaveUserIsCalled_thenWarehouseAssistantIsSaved() {
        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);
        when(rolePersistencePort.getRoleById(defaultUser.getUserRole().getRoleId())).thenReturn(defaultUser.getUserRole());

        userUseCase.saveWarehouseAssistant(defaultUser);

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, times(1)).getRoleById(defaultUser.getUserRole().getRoleId());
        verify(userPersistencePort, times(1)).saveUser(defaultUser);
    }

    @Test
    void givenUserAlreadyExists_whenSaveWarehouseAssistantIsCalled_thenThrowsException() {

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.saveWarehouseAssistant(defaultUser));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(any());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenInvalidEmailFormat_whenSaveWarehouseAssistantIsCalled_thenThrowsException() {

        defaultUser.setUserEmail("invalid-email-format");

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);

        assertThrows(UserInvalidEmailFormatException.class, () -> userUseCase.saveWarehouseAssistant(defaultUser));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(any());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenInvalidPhoneFormat_whenSaveWarehouseAssistantIsCalled_thenThrowsException() {

        defaultUser.setUserPhone("invalid-phone-format");

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);

        assertThrows(UserInvalidPhoneFormatException.class, () -> userUseCase.saveWarehouseAssistant(defaultUser));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(any());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenInvalidDocumentFormat_whenSaveWarehouseAssistantIsCalled_thenThrowsException() {

        defaultUser.setUserDocument("invalid_document");

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);

        assertThrows(UserInvalidDocumentFormatException.class, () -> userUseCase.saveWarehouseAssistant(defaultUser));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(any());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenUserUnderage_whenSaveWarehouseAssistantIsCalled_thenThrowsException() {

        defaultUser.setUserBirthdate(LocalDate.of(2010, 1, 1));

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);

        assertThrows(UserUnderageException.class, () -> userUseCase.saveWarehouseAssistant(defaultUser));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(any());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }
}
