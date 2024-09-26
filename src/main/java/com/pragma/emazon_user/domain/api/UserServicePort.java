package com.pragma.emazon_user.domain.api;

import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.infrastructure.out.jpa.entity.RoleEnum;

public interface UserServicePort {

    void saveUser(User user, RoleEnum roleName);

    boolean checkIfUserExists(String userEmail);

    boolean checkIfUserExistsByDocument(String userDocument);

    boolean checkIfUserExistsByPhone(String userPhone);

}
