package com.swynx.linkpeer_backend.user.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;

import com.swynx.linkpeer_backend.user.dto.request.UserUpdateRequest;
import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found!"));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, UserUpdateRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found!"));

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getBranch() != null) {
            user.setBranch(request.getBranch());
        }

        if (request.getDepartment() != null) {
            user.setDepartment(request.getDepartment());
        }

        if (request.getGraduatingYear() != null) {
            user.setGraduatingYear(request.getGraduatingYear());
        }

        if (request.getStream() != null) {
            user.setStream(request.getStream());
        }

        if (request.getDesignation() != null) {
            user.setDesignation(request.getDesignation());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        if (request.getDescription() != null) {
            user.setDescription(request.getDescription());
        }

        if (request.getGithub() != null) {
            user.setGithub(request.getGithub());
        }

        if (request.getLink2() != null) {
            user.setLink2(request.getLink2());
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
    userRepository.deleteById(id);
    }
}
