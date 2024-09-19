package com.pragma.emazon_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    String username;
    String message;
    String accessToken;
    Boolean status;

}
