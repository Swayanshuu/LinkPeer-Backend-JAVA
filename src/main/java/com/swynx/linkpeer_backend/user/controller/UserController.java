package com.swynx.linkpeer_backend.user.controller;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.user.dto.request.UserUpdateRequest;
import com.swynx.linkpeer_backend.user.dto.response.UserResponse;
import com.swynx.linkpeer_backend.user.dto.response.UserUpdateResponse;
import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.mapper.UserMapper;
import com.swynx.linkpeer_backend.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(userMapper.toResponse(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable String id,
            @Valid @RequestBody UserUpdateRequest request) {

        User updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(
                userMapper.toUpdateResponse(updatedUser)
        );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
