package com.swynx.linkpeer_backend.user.service;

import com.swynx.linkpeer_backend.user.entity.User;

import java.util.Optional;

public interface UserService {

    User getUserById(String id);
    Optional<User> getUserByEmail(String email);
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(String id);
}
