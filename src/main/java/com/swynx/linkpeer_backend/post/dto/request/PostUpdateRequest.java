package com.swynx.linkpeer_backend.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostUpdateRequest {
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    private String link;

    private String imageUrl;

    private String fileUrl;

    private String fileName;

    private String fileType;

    private List<String> imageUrls;
}
