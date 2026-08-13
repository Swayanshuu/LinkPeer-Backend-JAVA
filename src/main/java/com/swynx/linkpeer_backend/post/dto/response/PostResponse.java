package com.swynx.linkpeer_backend.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponse {
    private String userName;
    private String userPhoto;
    private String postType;
    private String title;
    private String content;
    private String link;
    private LocalDateTime createdAt;
    private String userType;
    private String department;
    private LocalDateTime updatedAt;
    private String branch;
    private List<String> imageUrls;
}
