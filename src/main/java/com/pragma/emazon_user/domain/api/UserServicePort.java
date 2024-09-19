package com.pragma.emazon_user.domain.api;

import com.pragma.emazon_user.domain.model.User;

public interface UserServicePort {

    void saveUser(User user, Integer roleId);

    boolean checkIfUserExists(String userEmail);

    boolean checkIfUserExistsByDocument(String userDocument);

    boolean checkIfUserExistsByPhone(String userPhone);

}
