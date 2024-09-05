package com.pragma.emazon_user.domain.exception.user;

import com.pragma.emazon_user.domain.utils.Constants;

public class UserInvalidDocumentFormatException extends RuntimeException{

    public UserInvalidDocumentFormatException(String userDocument) {
        super(Constants.USER_INVALID_DOCUMENT_FORMAT_EXCEPTION_MESSAGE + userDocument);
    }
}
