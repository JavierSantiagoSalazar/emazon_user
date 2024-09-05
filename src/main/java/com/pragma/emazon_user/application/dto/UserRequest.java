package com.pragma.emazon_user.application.dto;

import com.pragma.emazon_user.application.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = Constants.USER_NAME_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_NAME_MUST_NOT_BE_NULL)
    @Size(max = Constants.USER_NAME_LENGTH, message = Constants.USER_NAME_LENGTH_EXCEEDED)
    private String userName;

    @NotBlank(message = Constants.USER_LAST_NAME_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_LAST_NAME_MUST_NOT_BE_NULL)
    @Size(max = Constants.USER_LAST_NAME_LENGTH, message = Constants.USER_LAST_NAME_LENGTH_EXCEEDED)
    private String userLastName;

    @NotBlank(message = Constants.USER_DOCUMENT_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_DOCUMENT_MUST_NOT_BE_NULL)
    @Size(max = Constants.USER_DOCUMENT_LENGTH, message = Constants.USER_DOCUMENT_LENGTH_EXCEEDED)
    private String userDocument;

    @NotBlank(message = Constants.USER_PHONE_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_PHONE_MUST_NOT_BE_NULL)
    @Size(max = Constants.USER_PHONE_LENGTH, message = Constants.USER_PHONE_LENGTH_EXCEEDED)
    @Pattern(regexp = Constants.USER_PHONE_REGEX, message = Constants.USER_PHONE_PATTERN_UNFULFILLED)
    private String userPhone;

    @NotBlank(message = Constants.USER_BIRTHDATE_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_BIRTHDATE_MUST_NOT_BE_NULL)
    @Pattern(regexp = Constants.USER_BIRTHDATE_REGEX, message = Constants.USER_BIRTHDATE_PATTERN_UNFULFILLED)
    private String userBirthdate;

    @NotBlank(message = Constants.USER_EMAIL_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_EMAIL_MUST_NOT_BE_NULL)
    @Size(max = Constants.USER_EMAIL_LENGTH, message = Constants.USER_EMAIL_LENGTH_EXCEEDED)
    @Email(message = Constants.USER_EMAIL_INVALID)
    private String userEmail;

    @NotBlank(message = Constants.USER_PASSWORD_MUST_NOT_BE_BLANK)
    @NotNull(message = Constants.USER_PASSWORD_MUST_NOT_BE_NULL)
    @Pattern(regexp = Constants.USER_PASSWORD_REGEX, message = Constants.USER_PASSWORD_CANT_CONTAIN_WHITE_SPACES)
    private String userPassword;

}
