package com.pragma.emazon_user.domain.exception.role;


import com.pragma.emazon_user.domain.utils.Constants;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
        super(Constants.ROLE_DOES_NOT_EXIST);
    }
}
