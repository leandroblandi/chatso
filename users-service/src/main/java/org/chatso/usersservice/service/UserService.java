package org.chatso.usersservice.service;

import org.chatso.usersservice.request.CreateUserRequest;
import org.chatso.usersservice.request.UpdateUserRequest;
import org.chatso.usersservice.response.UserResponse;

import java.util.Optional;

public interface UserService {
    Optional<UserResponse> findUserByUuid(String uuid);
    Optional<UserResponse> findUserByUsername(String username);

    void createUser(CreateUserRequest request);
    void updateUser(String uuid, UpdateUserRequest request);

    void deactivateUser(String uuid);
}
