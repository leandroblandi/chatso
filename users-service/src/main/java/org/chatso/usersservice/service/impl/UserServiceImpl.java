package org.chatso.usersservice.service.impl;

import org.chatso.usersservice.entity.UserEntity;
import org.chatso.usersservice.mapper.UserMapper;
import org.chatso.usersservice.repository.UserRepository;
import org.chatso.usersservice.request.CreateUserRequest;
import org.chatso.usersservice.request.UpdateUserRequest;
import org.chatso.usersservice.response.UserResponse;
import org.chatso.usersservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserResponse> findUserByUuid(String uuid) {
        Optional<UserEntity> userOptional = userRepository.findById(uuid);
        return processAndMap(userOptional);
    }

    @Override
    public Optional<UserResponse> findUserByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        return processAndMap(userOptional);
    }

    @Override
    public void createUser(CreateUserRequest request) {
        UserEntity userEntity = userMapper.toEntity(request);
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void updateUser(String uuid, UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(uuid);
        update(userOptional, request);
    }

    @Override
    @Transactional
    public void deactivateUser(String uuid) {
        Optional<UserEntity> userOptional = userRepository.findById(uuid);
        deactivate(userOptional);
    }

    private void update(Optional<UserEntity> userOptional, UpdateUserRequest request) {
        userOptional.ifPresent(user -> {
            user.setDisplayName(request.getDisplayName());
            user.setBiography(request.getBiography());
            user.setAvatarUrl(request.getAvatarUrl());
            userRepository.save(user);
        });
    }

    private void deactivate(Optional<UserEntity> userOptional) {
        userOptional.ifPresent(user -> {
            user.setActive(false);
            userRepository.save(user);
        });
    }

    private Optional<UserResponse> processAndMap(Optional<UserEntity> userOptional) {
        if (userOptional.isPresent() && !userOptional.get().isActive()) {
            return Optional.empty();
        }
        return userOptional.map(userMapper::toResponse);
    }
}
