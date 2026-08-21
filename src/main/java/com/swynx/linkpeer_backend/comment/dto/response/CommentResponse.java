package com.swynx.linkpeer_backend.comment.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {

    private Long id;
    private String userName;
    private String userPhoto;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
