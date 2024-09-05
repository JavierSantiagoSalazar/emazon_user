package com.pragma.emazon_user.infrastructure.configuration.bean;

import com.pragma.emazon_user.domain.api.UserServicePort;
import com.pragma.emazon_user.domain.spi.RolePersistencePort;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import com.pragma.emazon_user.domain.usecase.UserUseCase;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.RoleRepository;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public RolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort());
    }

}
