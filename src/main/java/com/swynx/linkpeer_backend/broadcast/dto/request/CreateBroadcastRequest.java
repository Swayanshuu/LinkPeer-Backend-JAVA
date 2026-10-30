
package com.swynx.linkpeer_backend.broadcast.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateBroadcastRequest(
        @NotBlank String title,
        @NotBlank String message,
        String imageUrl,
        String linkUrl,
        @Pattern(
                regexp = "(?i)all|student|alumni|faculty",
                message = "Invalid audience"
        )
        String audience
) {}
