package com.pragma.emazon_user.application.handler.user;

import com.pragma.emazon_user.application.dto.UserRequest;
import jakarta.validation.Valid;

public interface UserHandler {

    void createWarehouseAssistant(@Valid UserRequest userRequest);

}
