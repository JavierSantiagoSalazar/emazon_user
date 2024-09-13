package com.pragma.emazon_user.infrastructure.configuration.security;

import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.infrastructure.configuration.security.exceptionhandler.CustomAuthenticationEntryPoint;
import com.pragma.emazon_user.infrastructure.configuration.security.filter.JwtValidatorFilter;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtValidatorFilter jwtValidatorFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(http -> http.
                        requestMatchers(
                                Constants.OPEN_API_SWAGGER_UI_HTML,
                                Constants.OPEN_API_SWAGGER_UI,
                                Constants.OPEN_API_V3_API_DOCS
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
                        .requestMatchers(HttpMethod.POST, Constants.WAREHOUSE_ASSISTANT_URL)
                            .hasRole(RoleEnum.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint))
                .build();
    }

}
