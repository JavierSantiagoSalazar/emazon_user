package com.pragma.emazon_user.domain.exception.user;

import com.pragma.emazon_user.domain.utils.Constants;

public class UserUnderageException extends RuntimeException{

    public UserUnderageException(String userBirthday) {
        super(Constants.USER_UNDERAGE_EXCEPTION_MESSAGE + userBirthday);
    }
}
