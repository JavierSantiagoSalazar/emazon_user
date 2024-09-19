package com.pragma.emazon_user.domain.usecase;

import com.pragma.emazon_user.domain.api.UserServicePort;
import com.pragma.emazon_user.domain.exception.user.UserAlreadyExistsException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidDocumentFormatException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidEmailFormatException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidPhoneFormatException;
import com.pragma.emazon_user.domain.exception.user.UserUnderageException;
import com.pragma.emazon_user.domain.model.Role;
import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.domain.spi.PasswordEncoderPort;
import com.pragma.emazon_user.domain.spi.RolePersistencePort;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import com.pragma.emazon_user.domain.utils.Constants;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
public class UserUseCase implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public void saveUser(User user, Integer roleId) {

        validateUser(user);

        Role role = rolePersistencePort.getRoleById(roleId);
        String password = user.getUserPassword();

        user.setUserRole(role);
        user.setUserPassword(passwordEncoderPort.encode(password));

        userPersistencePort.saveUser(user);
    }

    @Override
    public boolean checkIfUserExists(String userEmail) {
        return userPersistencePort.checkIfUserExists(userEmail);
    }

    @Override
    public boolean checkIfUserExistsByDocument(String userDocument) {
        return userPersistencePort.checkIfUserExistsByDocument(userDocument);
    }

    @Override
    public boolean checkIfUserExistsByPhone(String userPhone) {
        return userPersistencePort.checkIfUserExistsByPhone(userPhone);
    }

    private void validateUser(User user) {
        validateEmail(user.getUserEmail());
        validatePhone(user.getUserPhone());
        validateDocument(user.getUserDocument());
        validateBirthdate(user.getUserBirthdate());
    }

    private void validateEmail(String email) {
        if (checkIfUserExists(email)) {
            throw new UserAlreadyExistsException(email);
        }
        if (!isValidEmail(email)) {
            throw new UserInvalidEmailFormatException(email);
        }
    }

    private void validatePhone(String phone) {
        if (checkIfUserExistsByPhone(phone)) {
            throw new UserAlreadyExistsException(phone);
        }
        if (!isValidPhone(phone)) {
            throw new UserInvalidPhoneFormatException(phone);
        }
    }

    private void validateDocument(String document) {
        if (checkIfUserExistsByDocument(document)) {
            throw new UserAlreadyExistsException(document);
        }
        if (!document.matches(Constants.USER_DOCUMENT_REGEX)) {
            throw new UserInvalidDocumentFormatException(document);
        }
    }

    private void validateBirthdate(LocalDate birthdate) {
        if (!isOfLegalAge(birthdate)) {
            throw new UserUnderageException(birthdate.toString());
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = Constants.USER_EMAIL_REGEX;
        return email.matches(emailRegex);
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = Constants.USER_PHONE_REGEX;
        return phone.matches(phoneRegex);
    }

    private boolean isOfLegalAge(LocalDate birthdate) {
        return Period.between(birthdate, LocalDate.now()).getYears() >= Constants.USER_MAX_AGE;
    }

}
