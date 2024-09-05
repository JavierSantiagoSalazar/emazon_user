package com.pragma.emazon_user.domain.api;

import com.pragma.emazon_user.domain.model.User;

public interface UserServicePort {

    void saveWarehouseAssistant(User user);

    boolean checkIfUserExists(String userEmail);

    boolean checkIfUserExistsByDocument(String userDocument);

    boolean checkIfUserExistsByPhone(String userPhone);

}
