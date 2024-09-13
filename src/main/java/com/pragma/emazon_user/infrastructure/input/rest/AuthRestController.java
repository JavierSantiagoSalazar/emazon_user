package com.pragma.emazon_user.infrastructure.input.rest;

import com.pragma.emazon_user.application.dto.auth.AuthenticationResponseDto;
import com.pragma.emazon_user.application.dto.auth.LoginRequest;
import com.pragma.emazon_user.application.handler.auth.AuthenticationHandler;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.domain.utils.HttpStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationHandler authenticationHandler;

    @Operation(summary = Constants.LOGIN_SUCCESSFULLY, description = Constants.LOGIN_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HttpStatusCode.OK,
                    description = Constants.LOGIN_SUMMARY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthenticationResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_EMAIL_MUST_NOT_BE_BLANK,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_EMAIL_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_EMAIL_LENGTH_EXCEEDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_EMAIL_INVALID,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PASSWORD_MUST_NOT_BE_BLANK,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PASSWORD_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PASSWORD_CANT_CONTAIN_WHITE_SPACES,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.UNAUTHORIZED,
                    description = Constants.USER_INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.UNAUTHORIZED,
                    description = Constants.BAD_CREDENTIALS,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.UNAUTHORIZED,
                    description = Constants.INCORRECT_PASSWORD,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.UNAUTHORIZED,
                    description = Constants.INVALID_TOKEN,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.NOT_FOUND,
                    description = Constants.USER_DOES_NOT_EXIST,
                    content = @Content
            )
    })
    @PostMapping("/log-in")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody LoginRequest loginRequest){

        AuthenticationResponseDto authenticationResponseDto = authenticationHandler
                .loginUser(loginRequest);
        return new ResponseEntity<>(authenticationResponseDto, HttpStatus.OK);
    }

}