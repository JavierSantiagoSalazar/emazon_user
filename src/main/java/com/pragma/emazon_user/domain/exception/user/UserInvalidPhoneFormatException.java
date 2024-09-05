package com.pragma.emazon_user.domain.exception.user;

import com.pragma.emazon_user.domain.utils.Constants;

public class UserInvalidPhoneFormatException extends RuntimeException{

    public UserInvalidPhoneFormatException(String userPhone) {
        super(Constants.USER_INVALID_PHONE_FORMAT_EXCEPTION_MESSAGE + userPhone);
    }
}
