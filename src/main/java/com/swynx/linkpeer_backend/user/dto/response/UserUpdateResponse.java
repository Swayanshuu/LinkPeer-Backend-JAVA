package com.swynx.linkpeer_backend.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserUpdateResponse {
    private String id;
    private String name;
    private String email;
    private String branch;
    private String department;
    private Integer graduatingYear;
}
