package com.pragma.emazon_user.domain.spi;

import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;

public interface AuthenticationPersistencePort {

    AuthenticationResponse loginUser(Login login);

}
