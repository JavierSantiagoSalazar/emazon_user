package com.pragma.emazon_user.infrastructure.out.jpa.entity;

import com.pragma.emazon_user.domain.utils.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class Principal implements UserDetails {

    private final String userEmail;
    private final String userPassword;
    private final Collection<? extends GrantedAuthority> userRole;

    public Principal(String userEmail, String userPassword, Collection<? extends GrantedAuthority> userRole) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    public static Principal buildPrincipal(UserEntity userEntity) {
        return new Principal(
                userEntity.getUserEmail(),
                userEntity.getUserPassword(),
                List.of(new SimpleGrantedAuthority(
                                Constants.GRANTED_AUTHORITY_ROLE.concat(userEntity.getUserRole().getRoleName().name())
                        )
                )
        );
    }

}
