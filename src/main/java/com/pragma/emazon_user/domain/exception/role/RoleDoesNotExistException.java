package com.pragma.emazon_user.domain.exception.role;


import com.pragma.emazon_user.domain.utils.Constants;

public class RoleDoesNotExistException extends RuntimeException {

    public RoleDoesNotExistException(Integer roleId) {
        super(Constants.ROLE_DOES_NOT_EXIST + roleId.toString());
    }
}
