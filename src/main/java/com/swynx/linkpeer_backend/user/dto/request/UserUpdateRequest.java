package com.swynx.linkpeer_backend.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    private String branch;

    private String department;

    private Integer graduatingYear;

    private String stream;

    private String designation;

    private String phone;

    @Size(max = 150, message = "Description must not exceed 150 characters")
    private String description;

    private String github;

    private String link2;
}