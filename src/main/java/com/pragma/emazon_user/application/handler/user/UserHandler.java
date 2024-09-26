package com.pragma.emazon_user.application.handler.user;

import com.pragma.emazon_user.application.dto.UserRequest;

public interface UserHandler {

    void createWarehouseAssistant(UserRequest userRequest);

    void createClient(UserRequest userRequest);

}
