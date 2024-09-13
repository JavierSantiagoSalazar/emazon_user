package com.pragma.emazon_user.application.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthenticationResponseDto {

    String username;
    String message;
    String accessToken;
    Boolean status;

}
