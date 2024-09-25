package com.pragma.emazon_user.infrastructure.configuration.bean;

import com.pragma.emazon_user.domain.api.AuthenticationServicePort;
import com.pragma.emazon_user.domain.api.UserServicePort;
import com.pragma.emazon_user.domain.spi.AuthenticationPersistencePort;
import com.pragma.emazon_user.domain.spi.PasswordEncoderPort;
import com.pragma.emazon_user.domain.spi.RolePersistencePort;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import com.pragma.emazon_user.domain.usecase.AuthenticationUseCase;
import com.pragma.emazon_user.domain.usecase.UserUseCase;
import com.pragma.emazon_user.infrastructure.configuration.security.exceptionhandler.CustomAuthenticationEntryPoint;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.AuthenticationAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.PasswordEncoderAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.UserDetailsServiceAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.RoleRepository;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.UserRepository;
import com.pragma.emazon_user.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceAdapter userDetailsService;


    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public RolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public PasswordEncoderPort passwordEncoderPort() {
        return new PasswordEncoderAdapter(passwordEncoder());
    }

    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort(), passwordEncoderPort());
    }

    @Bean
    public AuthenticationServicePort userAuthenticationServicePort() {
        return new AuthenticationUseCase(authenticationPersistencePort());
    }

    @Bean
    public AuthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationAdapter(
                jwtUtils,
                passwordEncoder(),
                userDetailsService,
                userPersistencePort()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

}
