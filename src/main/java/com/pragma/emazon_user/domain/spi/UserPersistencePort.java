package com.pragma.emazon_user.domain.spi;


import com.pragma.emazon_user.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {

    void saveUser(User user);

    Boolean checkIfUserExists(String userEmail);

    Boolean checkIfUserExistsByDocument(String userDocument);

    Boolean checkIfUserExistsByPhone(String userPhone);

    Optional<Integer> getUserIdByEmail(String email);

}
