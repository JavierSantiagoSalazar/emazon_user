package com.pragma.emazon_user.application.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    /* --- VALIDATION CONSTANTS: USER--- */

    public static final String USER_NAME_MUST_NOT_BE_BLANK = "User name cannot be empty";
    public static final String USER_NAME_MUST_NOT_BE_NULL = "User name cannot be null";
    public static final String USER_NAME_LENGTH_EXCEEDED = "User name cannot exceed 150 characters";
    public static final int USER_NAME_LENGTH = 150;

    public static final String USER_LAST_NAME_MUST_NOT_BE_BLANK = "User last name cannot be empty";
    public static final String USER_LAST_NAME_MUST_NOT_BE_NULL = "User last name cannot be null";
    public static final String USER_LAST_NAME_LENGTH_EXCEEDED = "User last name cannot exceed 150 characters";
    public static final int USER_LAST_NAME_LENGTH = 150;

    public static final String USER_DOCUMENT_MUST_NOT_BE_BLANK = "User document cannot be empty";
    public static final String USER_DOCUMENT_MUST_NOT_BE_NULL = "User document cannot be null";
    public static final String USER_DOCUMENT_LENGTH_EXCEEDED = "User document cannot exceed 10 characters";
    public static final int USER_DOCUMENT_LENGTH = 10;

    public static final String USER_PHONE_MUST_NOT_BE_BLANK = "Phone number cannot be empty";
    public static final String USER_PHONE_MUST_NOT_BE_NULL = "Phone number cannot be null";
    public static final String USER_PHONE_LENGTH_EXCEEDED = "Phone number cannot exceed 13 characters";
    public static final String USER_PHONE_PATTERN_UNFULFILLED = "Phone number must contain only numbers and may start with + symbol";
    public static final int USER_PHONE_LENGTH = 13;
    public static final String USER_PHONE_REGEX = "^\\+?\\d+$";

    public static final String USER_BIRTHDATE_MUST_NOT_BE_BLANK = "Birthdate cannot be empty";
    public static final String USER_BIRTHDATE_MUST_NOT_BE_NULL = "Birthdate cannot be null";
    public static final String USER_BIRTHDATE_PATTERN_UNFULFILLED = "Birthdate must be in yyyy/mm/dd format";
    public static final String USER_BIRTHDATE_REGEX = "^\\d{4}/\\d{2}/\\d{2}$";

    public static final String USER_EMAIL_MUST_NOT_BE_BLANK = "Email cannot be empty";
    public static final String USER_EMAIL_MUST_NOT_BE_NULL = "Email cannot be null";
    public static final String USER_EMAIL_LENGTH_EXCEEDED = "Email cannot exceed 150 characters";
    public static final int USER_EMAIL_LENGTH = 150;
    public static final String USER_EMAIL_INVALID = "Invalid email";

    public static final String USER_PASSWORD_MUST_NOT_BE_BLANK = "Password cannot be empty";
    public static final String USER_PASSWORD_MUST_NOT_BE_NULL = "Password cannot be null";
    public static final String USER_PASSWORD_CANT_CONTAIN_WHITE_SPACES = "Password cannot contain white spaces";
    public static final String USER_PASSWORD_REGEX = "\\S+";

}
