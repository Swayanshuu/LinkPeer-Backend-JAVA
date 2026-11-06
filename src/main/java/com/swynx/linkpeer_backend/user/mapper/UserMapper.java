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
                .college(user.getCollege())
                .stream(user.getStream())
                .designation(user.getDesignation())
                .phone(user.getPhone())
                .github(user.getGithub())
                .link2(user.getLink2())
                .description(user.getDescription())
                .isVerified(user.getVerified())
                .facultyVerified(user.getFacultyVerified())
                .facultyVerificationImage(user.getFacultyVerificationImage())
                .subscriptionPlan(user.getSubscriptionPlan())
                .subscriptionStatus(user.getSubscriptionStatus())
                .createdAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
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
