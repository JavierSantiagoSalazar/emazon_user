package com.pragma.emazon_user.application.handler.auth;

import com.pragma.emazon_user.application.dto.auth.AuthenticationResponseDto;
import com.pragma.emazon_user.application.dto.auth.LoginRequest;

public interface AuthenticationHandler {

    AuthenticationResponseDto loginUser(LoginRequest loginRequest);

}
