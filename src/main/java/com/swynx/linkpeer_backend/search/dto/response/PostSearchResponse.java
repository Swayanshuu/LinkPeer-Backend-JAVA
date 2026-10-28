
package com.swynx.linkpeer_backend.search.dto.response;

import java.time.LocalDateTime;

public record PostSearchResponse(
        Long id,
        String content,
        String userId,
        String userName,
        String userPhoto,
        String postType,
        String title,
        String imageUrl,
        LocalDateTime createdAt
) {}

