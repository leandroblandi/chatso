package org.chatso.usersservice.controller;

import org.chatso.usersservice.request.CreateUserRequest;
import org.chatso.usersservice.request.UpdateUserRequest;
import org.chatso.usersservice.response.UserResponse;
import org.chatso.usersservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> findByUsername(@RequestParam String username) {
        Optional<UserResponse> userOptional = userService.findUserByUsername(username);
        return ResponseEntity.of(userOptional);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> findByUuid(@PathVariable String uuid) {
        Optional<UserResponse> userOptional = userService.findUserByUuid(uuid);
        return ResponseEntity.of(userOptional);
    }


    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Void> updateUser(@PathVariable String uuid, @RequestBody UpdateUserRequest request) {
        userService.updateUser(uuid, request);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deactivateUser(@PathVariable String uuid) {
        userService.deactivateUser(uuid);
        return ResponseEntity.status(200).build();
    }
}
