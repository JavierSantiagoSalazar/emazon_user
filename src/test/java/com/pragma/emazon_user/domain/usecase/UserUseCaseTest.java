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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
                null
        );
    }

    @Test
    void givenValidUserRequest_whenSaveWarehouseAssistant_thenWarehouseAssistantIsSaved() {

        int warehouseAssistantRoleId = 1;

        Role warehouseAssistantRole = new Role(warehouseAssistantRoleId, "aux_bodega", "description", Set.of(new Permission(1, "READ")));

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);
        when(rolePersistencePort.getRoleById(warehouseAssistantRoleId)).thenReturn(warehouseAssistantRole);
        when(passwordEncoderPort.encode("password123")).thenReturn("encodedPassword");

        userUseCase.saveUser(defaultUser, warehouseAssistantRoleId);

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, times(1)).getRoleById(warehouseAssistantRoleId);
        verify(passwordEncoderPort, times(1)).encode("password123");
        verify(userPersistencePort, times(1)).saveUser(defaultUser);

        assertEquals(warehouseAssistantRole, defaultUser.getUserRole());
        assertEquals("encodedPassword", defaultUser.getUserPassword());
    }

    @Test
    void givenValidUserRequest_whenSaveClient_thenClientIsSaved() {

        int clientRoleId = 3;

        Role clientRole = new Role(clientRoleId, "client", "Client role", Set.of(new Permission(2, "WRITE")));

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);
        when(rolePersistencePort.getRoleById(clientRoleId)).thenReturn(clientRole);
        when(passwordEncoderPort.encode("password123")).thenReturn("encodedPassword");

        userUseCase.saveUser(defaultUser, clientRoleId);

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, times(1)).getRoleById(clientRoleId);
        verify(passwordEncoderPort, times(1)).encode("password123");
        verify(userPersistencePort, times(1)).saveUser(defaultUser);

        assertEquals(clientRole, defaultUser.getUserRole());
        assertEquals("encodedPassword", defaultUser.getUserPassword());
    }

    @Test
    void givenUserAlreadyExists_whenSaveUserIsCalled_thenThrowsException() {

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.saveUser(defaultUser, 1));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(anyInt());
        verify(passwordEncoderPort, never()).encode(anyString());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenInvalidEmailFormat_whenSaveUserIsCalled_thenThrowsException() {

        defaultUser.setUserEmail("invalid-email-format");

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);

        assertThrows(UserInvalidEmailFormatException.class, () -> userUseCase.saveUser(defaultUser, 1));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(anyInt());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenInvalidPhoneFormat_whenSaveUserIsCalled_thenThrowsException() {

        defaultUser.setUserPhone("invalid-phone-format");

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);
        when(userPersistencePort.checkIfUserExistsByPhone(defaultUser.getUserPhone())).thenReturn(false);

        assertThrows(UserInvalidPhoneFormatException.class, () -> userUseCase.saveUser(defaultUser, 1));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(userPersistencePort, times(1)).checkIfUserExistsByPhone(defaultUser.getUserPhone());
        verify(rolePersistencePort, never()).getRoleById(anyInt());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenInvalidDocumentFormat_whenSaveUserIsCalled_thenThrowsException() {

        defaultUser.setUserDocument("invalid_document");

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);
        when(userPersistencePort.checkIfUserExistsByDocument(defaultUser.getUserDocument())).thenReturn(false);

        assertThrows(UserInvalidDocumentFormatException.class, () -> userUseCase.saveUser(defaultUser, 1));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(userPersistencePort, times(1)).checkIfUserExistsByDocument(defaultUser.getUserDocument());
        verify(rolePersistencePort, never()).getRoleById(anyInt());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

    @Test
    void givenUserUnderage_whenSaveUserIsCalled_thenThrowsException() {

        defaultUser.setUserBirthdate(LocalDate.of(2010, 1, 1));

        when(userPersistencePort.checkIfUserExists(defaultUser.getUserEmail())).thenReturn(false);

        assertThrows(UserUnderageException.class, () -> userUseCase.saveUser(defaultUser, 1));

        verify(userPersistencePort, times(1)).checkIfUserExists(defaultUser.getUserEmail());
        verify(rolePersistencePort, never()).getRoleById(anyInt());
        verify(userPersistencePort, never()).saveUser(defaultUser);
    }

}
