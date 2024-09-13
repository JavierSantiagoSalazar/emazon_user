package com.pragma.emazon_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Login {

    private String userEmail;
    private String userPassword;

}
