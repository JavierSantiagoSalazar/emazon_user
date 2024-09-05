package com.pragma.emazon_user.domain.spi;


import com.pragma.emazon_user.domain.model.User;

public interface UserPersistencePort {

    void saveUser(User user);

    Boolean checkIfUserExists(String userEmail);

    Boolean checkIfUserExistsByDocument(String userDocument);

    Boolean checkIfUserExistsByPhone(String userPhone);

}
