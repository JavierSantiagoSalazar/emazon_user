package com.pragma.emazon_user.domain.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    /* --- EXCEPTIONS CONSTANTS: USER --- */

    public static final String USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Usuario ya registrado con: ";
    public static final String USER_INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE = "Formato de email inválido: ";
    public static final String USER_INVALID_PHONE_FORMAT_EXCEPTION_MESSAGE = "El teléfono debe contener solo números y puede comenzar con el símbolo: + , El teléfono ingresado: ";
    public static final String USER_INVALID_DOCUMENT_FORMAT_EXCEPTION_MESSAGE = "Formato de documento inválido: ";
    public static final String USER_UNDERAGE_EXCEPTION_MESSAGE = "El usuario no puede ser menor de edad. Fecha de nacimiento: ";

    /* --- EXCEPTIONS CONSTANTS: ROLE --- */

    public static final String ROLE_DOES_NOT_EXIST = "El rol no existe con ID: ";

    /* --- OPENAPI CONSTANTS --- */

    public static final String OPEN_API_TITLE = "Emazon User API";
    public static final String OPEN_API_TERMS = "http://swagger.io/terms/";
    public static final String OPEN_API_LICENCE_NAME = "Apache 2.0";
    public static final String OPEN_API_LICENCE_URL = "http://springdoc.org";
    public static final String OPEN_API_APP_DESCRIPTION = "${appDescription}";
    public static final String OPEN_API_APP_VERSION = "${appVersion}";

    public static final String WAREHOUSE_ASSISTANT_SUMMARY = "Add new warehouse assistant";

    public static final String WAREHOUSE_ASSISTANT_CREATED = "Warehouse assistant created";

    public static final String WAREHOUSE_ASSISTANT_ALREADY_EXISTS = "Warehouse assistant already exists";
    public static final String INVALID_REQUEST = "Invalid request";

    public static final String USER_NAME_MUST_NOT_BE_BLANK = "User name cannot be blank";
    public static final String USER_NAME_MUST_NOT_BE_NULL = "User name cannot be null";
    public static final String USER_NAME_LENGTH_EXCEEDED = "User name length exceeded";

    public static final String USER_LAST_NAME_MUST_NOT_BE_BLANK = "User last name cannot be blank";
    public static final String USER_LAST_NAME_MUST_NOT_BE_NULL = "User last name cannot be null";
    public static final String USER_LAST_NAME_LENGTH_EXCEEDED = "User last name length exceeded";

    public static final String USER_DOCUMENT_MUST_NOT_BE_BLANK = "User document cannot be blank";
    public static final String USER_DOCUMENT_MUST_NOT_BE_NULL = "User document cannot be null";
    public static final String USER_DOCUMENT_LENGTH_EXCEEDED = "User document length exceeded";

    public static final String USER_PHONE_MUST_NOT_BE_BLANK = "User phone cannot be blank";
    public static final String USER_PHONE_MUST_NOT_BE_NULL = "User phone cannot be null";
    public static final String USER_PHONE_LENGTH_EXCEEDED = "User phone length exceeded";
    public static final String USER_PHONE_PATTERN_UNFULFILLED = "User phone pattern unfulfilled";

    public static final String USER_BIRTHDATE_MUST_NOT_BE_BLANK = "User birthdate cannot be blank";
    public static final String USER_BIRTHDATE_MUST_NOT_BE_NULL = "User birthdate cannot be null";
    public static final String USER_BIRTHDATE_PATTERN_UNFULFILLED = "User birthdate pattern unfulfilled";

    public static final String USER_EMAIL_MUST_NOT_BE_BLANK = "User email cannot be blank";
    public static final String USER_EMAIL_MUST_NOT_BE_NULL = "User email cannot be null";
    public static final String USER_EMAIL_LENGTH_EXCEEDED = "User email length exceeded";
    public static final String USER_EMAIL_INVALID = "User email invalid";

    public static final String USER_PASSWORD_MUST_NOT_BE_BLANK = "User password cannot be blank";
    public static final String USER_PASSWORD_MUST_NOT_BE_NULL = "User password cannot be null";
    public static final String USER_PASSWORD_CANT_CONTAIN_WHITE_SPACES = "User password cannot contain white spaces";

    /* --- REGEX CONSTANTS --- */

    public static final String USER_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String USER_PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String USER_DOCUMENT_REGEX = "\\d+";
    public static final Integer USER_MAX_AGE = 18;

}