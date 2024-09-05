package com.pragma.emazon_user.domain.exception.user;

import com.pragma.emazon_user.domain.utils.Constants;

public class UserInvalidEmailFormatException extends RuntimeException{

    public UserInvalidEmailFormatException(String userEmail) {
        super(Constants.USER_INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE + userEmail);
    }
}
