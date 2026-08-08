package com.swynx.linkpeer_backend.user.mapper;

import com.swynx.linkpeer_backend.user.dto.response.UserResponse;
import com.swynx.linkpeer_backend.user.dto.response.UserUpdateResponse;
import com.swynx.linkpeer_backend.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .photoUrl(user.getPhotoUrl())
                .role(user.getRole())
                .branch(user.getBranch())
                .department(user.getDepartment())
                .userType(user.getUserType())
                .graduatingYear(user.getGraduatingYear())
                .profileCompleted(user.getProfileCompleted())
                .build();
    }

    public UserUpdateResponse toUpdateResponse(User user) {
        return UserUpdateResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .branch(user.getBranch())
                .department(user.getDepartment())
                .graduatingYear(user.getGraduatingYear())
                .build();
    }
}
