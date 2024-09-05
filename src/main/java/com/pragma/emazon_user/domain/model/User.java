package com.pragma.emazon_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private Integer userId;
    private String userName;
    private String userLastName;
    private String userDocument;
    private String userPhone;
    private LocalDate userBirthdate;
    private String userEmail;
    private String userPassword;
    private Role userRole;

}