package com.swynx.linkpeer_backend.broadcast.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BroadcastResponse(UUID id,
                                String title,
                                String message,
                                String imageUrl,
                                String linkUrl,
                                String audience,
                                String createdBy,
                                OffsetDateTime createdAt,
                                Integer totalRecipients,
                                Integer totalOpens,
                                Integer clickCount,
                                Integer linkClicks)
{
}
