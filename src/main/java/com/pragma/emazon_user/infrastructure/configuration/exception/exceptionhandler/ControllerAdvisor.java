package com.pragma.emazon_user.infrastructure.configuration.exception.exceptionhandler;

import com.pragma.emazon_user.domain.exception.user.UserAlreadyExistsException;
import com.pragma.emazon_user.infrastructure.configuration.exception.dto.Response;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}
