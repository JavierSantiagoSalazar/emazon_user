package com.pragma.emazon_user.domain.exception.user;

import com.pragma.emazon_user.domain.utils.Constants;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String userEmail) {
        super(Constants.USER_ALREADY_EXISTS_EXCEPTION_MESSAGE + userEmail);
    }
}
