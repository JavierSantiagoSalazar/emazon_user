package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserJpaAdapterTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserEntityMapper userEntityMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private User defaultUser;
    private UserEntity defaultUserEntity;

    @BeforeEach
    public void setUp() {
        Role defaultRole = new Role(1, "aux_bodega", "description");
        defaultUser = new User(
                1,
                "John",
                "Doe",
                "123456789",
                "+1234567890",
                LocalDate.of(1990, 1, 1),
                "johndoe@example.com",
                "password",
                defaultRole
        );

        defaultUserEntity = new UserEntity(
                1,
                "John",
                "Doe",
                "123456789",
                "+1234567890",
                LocalDate.of(1990, 1, 1),
                "johndoe@example.com",
                "encodedPassword",
                new RoleEntity(1, "aux_bodega", "description", List.of())
        );
    }


    @Test
    void givenUser_whenSaveUserIsCalled_thenUserIsSaved() {

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        when(userEntityMapper.toEntity(defaultUser)).thenReturn(defaultUserEntity);

        userJpaAdapter.saveUser(defaultUser);

        verify(passwordEncoder, times(1)).encode("password");
        verify(userEntityMapper, times(1)).toEntity(defaultUser);
        verify(userRepository, times(1)).save(defaultUserEntity);
    }

    @Test
    void givenUserEmailAlreadyExists_whenCheckIfUserExists_thenReturnTrue() {

        when(userRepository.findByUserEmail(defaultUser.getUserEmail())).thenReturn(Optional.of(defaultUserEntity));

        Boolean result = userJpaAdapter.checkIfUserExists(defaultUser.getUserEmail());

        verify(userRepository, times(1)).findByUserEmail(defaultUser.getUserEmail());
        assertTrue(result);
    }

    @Test
    void givenUserEmailDoesNotExist_whenCheckIfUserExists_thenReturnFalse() {

        when(userRepository.findByUserEmail(defaultUser.getUserEmail())).thenReturn(Optional.empty());

        Boolean result = userJpaAdapter.checkIfUserExists(defaultUser.getUserEmail());

        verify(userRepository, times(1)).findByUserEmail(defaultUser.getUserEmail());
        assertFalse(result);
    }
}
