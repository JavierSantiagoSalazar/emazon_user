package com.pragma.emazon_user.application.handler.user;

import com.pragma.emazon_user.application.dto.UserRequest;
import com.pragma.emazon_user.application.mappers.UserRequestMapper;
import com.pragma.emazon_user.domain.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {

    private static final Integer WAREHOUSE_ASSISTANT_ROLE_ID = 1;
    private static final Integer CLIENT_ROLE_ID = 3;

    private final UserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void createWarehouseAssistant(UserRequest userRequest) {
        normalizeUserRequest(userRequest);
        userServicePort.saveUser(userRequestMapper.toDomain(userRequest), WAREHOUSE_ASSISTANT_ROLE_ID);
    }

    @Override
    public void createClient(UserRequest userRequest) {
        normalizeUserRequest(userRequest);
        userServicePort.saveUser(userRequestMapper.toDomain(userRequest), CLIENT_ROLE_ID);
    }

    private void normalizeUserRequest(UserRequest userRequest) {

        userRequest.setUserName(userRequest.getUserName().trim());
        userRequest.setUserLastName(userRequest.getUserLastName().trim());
        userRequest.setUserDocument(userRequest.getUserDocument().trim());
        userRequest.setUserPhone(userRequest.getUserPhone().trim());
        userRequest.setUserEmail(userRequest.getUserEmail().trim());
        userRequest.setUserPassword(userRequest.getUserPassword().trim());
    }

}
