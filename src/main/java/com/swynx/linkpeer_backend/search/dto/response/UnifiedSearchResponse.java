
package com.swynx.linkpeer_backend.search.dto.response;

import org.springframework.data.domain.Page;

public record UnifiedSearchResponse(
        Page<UserSearchResponse> users,
        Page<PostSearchResponse> posts
) {}
