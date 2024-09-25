package com.pragma.emazon_user.domain.exception.user;

import com.pragma.emazon_user.domain.utils.Constants;

public class UserNotFoundWithEmailException extends RuntimeException{

    public UserNotFoundWithEmailException(String userEmail) {
        super(Constants.USER_NOT_FOUND_WITH_EMAIL_EXCEPTION_MESSAGE + userEmail);
    }
}
