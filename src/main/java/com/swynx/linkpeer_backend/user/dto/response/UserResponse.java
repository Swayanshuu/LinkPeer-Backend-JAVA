package com.swynx.linkpeer_backend.user.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String photoUrl;
    private String role;
    private String branch;
    private String department;
    private String userType;
    private Integer graduatingYear;
    private Boolean profileCompleted;

}
