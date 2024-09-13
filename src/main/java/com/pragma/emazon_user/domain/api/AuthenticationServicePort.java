package com.pragma.emazon_user.domain.api;

import com.pragma.emazon_user.domain.model.AuthenticationResponse;
import com.pragma.emazon_user.domain.model.Login;

public interface AuthenticationServicePort {

    AuthenticationResponse loginUser(Login login);

}
