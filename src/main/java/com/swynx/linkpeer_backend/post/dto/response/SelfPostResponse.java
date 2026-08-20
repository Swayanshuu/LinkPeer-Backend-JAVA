package com.swynx.linkpeer_backend.post.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SelfPostResponse {
    private Long id;
    private String userId;
    private String userName;
    private String userPhoto;
    private String postType;
    private String title;
    private String content;
    private String link;
    private LocalDateTime createdAt;
    private String userType;
    private String department;
    private String imageUrl;
    private String fileUrl;
    private String fileName;
    private String fileType;
    private LocalDateTime updatedAt;
    private String branch;
    private String designation;
    private List<String> imageUrls;

    private long likeCount;
    private boolean likedByCurrentUser;
}
