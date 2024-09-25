package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.exception.user.UserNotFoundWithEmailException;
import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;
import com.pragma.emazon_user.domain.spi.AuthenticationPersistencePort;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationPersistencePort {

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceAdapter userDetailService;
    private final UserPersistencePort userPersistencePort;

    @Override
    public AuthenticationResponse loginUser(Login login) {

        String username = login.getUserEmail();
        String password = login.getUserPassword();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Integer userId = userPersistencePort.getUserIdByEmail(username)
                .orElseThrow(() -> new UserNotFoundWithEmailException(username));

        String accessToken = jwtUtils.createToken(authentication, userId);

        return AuthenticationResponse.builder()
                .username(username)
                .message(Constants.LOGIN_SUCCESSFULLY)
                .accessToken(accessToken)
                .status(true)
                .build();
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(Constants.BAD_CREDENTIALS);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(Constants.INCORRECT_PASSWORD);
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
