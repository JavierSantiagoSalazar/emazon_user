package com.pragma.emazon_user.domain.utils;

import lombok.Getter;

@Getter
public class HttpStatusCode {

    private HttpStatusCode() {
        throw new IllegalStateException("Utility class");
    }

    public static final String OK = "200";
    public static final String CREATED = "201";
    public static final String CONFLICT = "409";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "403";
    public static final String NOT_FOUND = "404";

}
