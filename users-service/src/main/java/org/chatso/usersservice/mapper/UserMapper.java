package org.chatso.usersservice.mapper;

import org.chatso.usersservice.entity.UserEntity;
import org.chatso.usersservice.request.CreateUserRequest;
import org.chatso.usersservice.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(CreateUserRequest request) {
        return UserEntity.builder()
                .uuid(request.getIdpUuid())
                .username(request.getUsername())
                .displayName(request.getDisplayName())
                .biography(request.getBiography())
                .avatarUrl(request.getAvatarUrl())
                .build();
    }

    public UserResponse toResponse(UserEntity entity) {
        return UserResponse.builder()
                .username(entity.getUsername())
                .displayName(entity.getDisplayName())
                .avatarUrl(entity.getAvatarUrl())
                .biography(entity.getBiography())
                .build();
    }
}
