package com.pragma.emazon_user.domain.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String COMMA_DELIMITER = ",";
    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String INVALID_TOKEN = "Token invalid";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access: ";
    public static final String SPRING_COMPONENT_MODEL = "spring";

    public static final String LOGIN_SUCCESSFULLY = "User logged successfully";
    public static final String BAD_CREDENTIALS = "Invalid username or password";
    public static final String GRANTED_AUTHORITY_ROLE = "ROLE_";

    public static final String INCORRECT_PASSWORD = "ROLE_";

    public static final String LOGIN_URL = "/auth/log-in";
    public static final String WAREHOUSE_ASSISTANT_URL = "/user/warehouse-assistant";
    public static final String CLIENT_URL = "/user/client";

    /* --- EXCEPTIONS CONSTANTS: USER --- */

    public static final String USER_ALREADY_EXISTS_EXCEPTION_MESSAGE = "User already registered with: ";
    public static final String USER_INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE = "Invalid email format: ";
    public static final String USER_INVALID_PHONE_FORMAT_EXCEPTION_MESSAGE = "Phone number must contain only numbers and may start with the symbol: +. The entered phone number: ";
    public static final String USER_INVALID_DOCUMENT_FORMAT_EXCEPTION_MESSAGE = "Invalid document format: ";
    public static final String USER_UNDERAGE_EXCEPTION_MESSAGE = "User cannot be underage. Date of birth: ";
    public static final String USER_DOES_NOT_EXIST = "User does not exist: ";
    public static final String USER_INVALID_EMAIL_FORMAT_EXCEPTION = "Invalid email format.";
    public static final String USER_INVALID_PHONE_FORMAT_EXCEPTION = "Phone number must contain only numbers and may start with the symbol: +. The entered phone number.";
    public static final String USER_INVALID_DOCUMENT_FORMAT_EXCEPTION = "Invalid document format.";
    public static final String USER_UNDERAGE_EXCEPTION = "User cannot be underage.";

    /* --- EXCEPTIONS CONSTANTS: ROLE --- */

    public static final String ROLE_DOES_NOT_EXIST = "Rol does not exist with ID: ";

    /* --- OPENAPI CONSTANTS --- */

    public static final String OPEN_API_TITLE = "Emazon User API";
    public static final String OPEN_API_TERMS = "http://swagger.io/terms/";
    public static final String OPEN_API_LICENCE_NAME = "Apache 2.0";
    public static final String OPEN_API_LICENCE_URL = "http://springdoc.org";
    public static final String OPEN_API_APP_DESCRIPTION = "${appDescription}";
    public static final String OPEN_API_APP_VERSION = "${appVersion}";
    public static final String OPEN_API_SWAGGER_UI_HTML = "/swagger-ui/**";
    public static final String OPEN_API_SWAGGER_UI = "/swagger-ui/";
    public static final String OPEN_API_V3_API_DOCS = "/v3/api-docs/**";

    public static final String WAREHOUSE_ASSISTANT_SUMMARY = "Add new warehouse assistant";
    public static final String CLIENT_SUMMARY = "Add new client";
    public static final String LOGIN_SUMMARY = "Allows a user to authenticate in the system";

    public static final String WAREHOUSE_ASSISTANT_CREATED = "Warehouse assistant created";
    public static final String CLIENT_CREATED = "Client created";

    public static final String WAREHOUSE_ASSISTANT_ALREADY_EXISTS = "Warehouse assistant already exists";
    public static final String CLIENT_ALREADY_EXISTS = "Client already exists";
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

    public static final String USER_EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String USER_PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String USER_DOCUMENT_REGEX = "\\d+";
    public static final Integer USER_MAX_AGE = 18;

}