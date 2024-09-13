package com.pragma.emazon_user.domain.usecase;

import com.pragma.emazon_user.domain.api.AuthenticationServicePort;
import com.pragma.emazon_user.domain.exception.user.UserInvalidEmailFormatException;
import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;
import com.pragma.emazon_user.domain.spi.AuthenticationPersistencePort;
import com.pragma.emazon_user.domain.utils.Constants;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationUseCase implements AuthenticationServicePort {

    private final AuthenticationPersistencePort authenticationPersistencePort;

    @Override
    public AuthenticationResponse loginUser(Login login) {

        if (!isValidEmail(login.getUserEmail())) {
            throw new UserInvalidEmailFormatException(login.getUserEmail());
        }

        return authenticationPersistencePort.loginUser(login);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = Constants.USER_EMAIL_REGEX;
        return email.matches(emailRegex);
    }

}
