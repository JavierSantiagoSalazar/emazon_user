package com.pragma.emazon_user.infrastructure.input.rest;

import com.pragma.emazon_user.application.dto.UserRequest;
import com.pragma.emazon_user.application.handler.UserHandler;
import com.pragma.emazon_user.domain.utils.Constants;
import com.pragma.emazon_user.domain.utils.HttpStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserHandler userHandler;

    @Value("${user.document.path}")
    private String userDocumentPath;

    @Operation(summary = Constants.WAREHOUSE_ASSISTANT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HttpStatusCode.CREATED,
                    description = Constants.WAREHOUSE_ASSISTANT_CREATED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.CONFLICT,
                    description = Constants.WAREHOUSE_ASSISTANT_ALREADY_EXISTS,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.INVALID_REQUEST,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_NAME_MUST_NOT_BE_BLANK,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_NAME_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_NAME_LENGTH_EXCEEDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_LAST_NAME_MUST_NOT_BE_BLANK,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_LAST_NAME_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_LAST_NAME_LENGTH_EXCEEDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_DOCUMENT_MUST_NOT_BE_BLANK,
                    content = @Content),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_DOCUMENT_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_DOCUMENT_LENGTH_EXCEEDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PHONE_MUST_NOT_BE_BLANK,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PHONE_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PHONE_LENGTH_EXCEEDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_PHONE_PATTERN_UNFULFILLED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_BIRTHDATE_MUST_NOT_BE_BLANK,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_BIRTHDATE_MUST_NOT_BE_NULL,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.USER_BIRTHDATE_PATTERN_UNFULFILLED,
                    content = @Content
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
            )
    })
    @PostMapping("/warehouseAssistant")
    public ResponseEntity<Void> createWarehouseAssistant(@Valid @RequestBody UserRequest userRequest) {

        userHandler.createWarehouseAssistant(userRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(userDocumentPath)
                .buildAndExpand(userRequest.getUserDocument())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}