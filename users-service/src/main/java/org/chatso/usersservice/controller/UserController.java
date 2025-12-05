package org.chatso.usersservice.controller;

import org.chatso.usersservice.response.UserResponse;
import org.chatso.usersservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> findByUuid(@PathVariable String uuid) {
        Optional<UserResponse> userOptional = userService.findUserByUuid(uuid);
        return ResponseEntity.of(userOptional);
    }

}
