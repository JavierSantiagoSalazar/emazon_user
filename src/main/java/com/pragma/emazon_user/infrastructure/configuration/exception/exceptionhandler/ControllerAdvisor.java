package com.pragma.emazon_user.infrastructure.configuration.exception.exceptionhandler;

import com.pragma.emazon_user.domain.exception.role.RoleNotFoundException;
import com.pragma.emazon_user.domain.exception.user.UserAlreadyExistsException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidDocumentFormatException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidEmailFormatException;
import com.pragma.emazon_user.domain.exception.user.UserInvalidPhoneFormatException;
import com.pragma.emazon_user.domain.exception.user.UserUnderageException;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.infrastructure.configuration.exception.dto.Response;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Response> handleUserAlreadyExistsException(
            UserAlreadyExistsException userAlreadyExistsException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.CONFLICT)
                        .message(userAlreadyExistsException.getMessage())
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {

        List<String> errors = methodArgumentNotValidException.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(errors.toString())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> handleBadCredentialsException(
            BadCredentialsException badCredentialsException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED)
                        .message(badCredentialsException.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UserInvalidEmailFormatException.class)
    public ResponseEntity<Response> handleUserInvalidEmailFormatException(
            UserInvalidEmailFormatException userInvalidEmailFormatException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(Constants.USER_INVALID_EMAIL_FORMAT_EXCEPTION)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserInvalidPhoneFormatException.class)
    public ResponseEntity<Response> handleUserInvalidPhoneFormatException(
            UserInvalidPhoneFormatException userInvalidPhoneFormatException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(Constants.USER_INVALID_PHONE_FORMAT_EXCEPTION)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserInvalidDocumentFormatException.class)
    public ResponseEntity<Response> handleUserInvalidDocumentFormatException(
            UserInvalidDocumentFormatException userInvalidDocumentFormatException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(Constants.USER_INVALID_DOCUMENT_FORMAT_EXCEPTION)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserUnderageException.class)
    public ResponseEntity<Response> handleUserUnderageException(
            UserUnderageException userUnderageException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(Constants.USER_UNDERAGE_EXCEPTION)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> handleUsernameNotFoundException(
            UsernameNotFoundException usernameNotFoundException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.NOT_FOUND)
                        .message(usernameNotFoundException.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Response> handleRoleDoesNotExistException(
            RoleNotFoundException roleNotFoundException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.NOT_FOUND)
                        .message(roleNotFoundException.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

}
