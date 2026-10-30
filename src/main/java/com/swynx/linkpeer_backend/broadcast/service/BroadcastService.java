
package com.swynx.linkpeer_backend.broadcast.service;

import com.swynx.linkpeer_backend.broadcast.dto.request.CreateBroadcastRequest;
import com.swynx.linkpeer_backend.broadcast.dto.response.BroadcastResponse;
import com.swynx.linkpeer_backend.broadcast.entity.Broadcast;
import com.swynx.linkpeer_backend.broadcast.repository.BroadcastRepository;

import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;
    private final UserRepository userRepository;

    public BroadcastService(
            BroadcastRepository broadcastRepository,
            UserRepository userRepository) {
        this.broadcastRepository = broadcastRepository;
        this.userRepository = userRepository;
    }

    public BroadcastResponse createBroadcast(CreateBroadcastRequest request, String createdBy) {

        Broadcast broadcast = new Broadcast();

        broadcast.setTitle(request.title());
        broadcast.setMessage(request.message());
        broadcast.setImageUrl(request.imageUrl());
        broadcast.setLinkUrl(request.linkUrl());

        String audience = request.audience() == null
                ? "all"
                : request.audience().trim().toLowerCase();
        broadcast.setAudience(audience);

        broadcast.setCreatedBy(createdBy);
        broadcast.setCreatedAt(OffsetDateTime.now());

        Broadcast savedBroadcast = broadcastRepository.save(broadcast);

        return mapToResponse(savedBroadcast);
    }


    public BroadcastResponse updateBroadcast(
            UUID id,
            CreateBroadcastRequest request) {

        Broadcast broadcast = broadcastRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Broadcast not found"
                        )
                );

        broadcast.setTitle(request.title());
        broadcast.setMessage(request.message());
        broadcast.setImageUrl(request.imageUrl());
        broadcast.setLinkUrl(request.linkUrl());

        if (request.audience() != null &&
                !request.audience().isBlank()) {

            broadcast.setAudience(
                    request.audience().trim().toLowerCase()
            );
        }

        return mapToResponse(broadcastRepository.save(broadcast));
    }

    public void deleteBroadcast(UUID id) {

        if (!broadcastRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Broadcast not found"
            );
        }

        broadcastRepository.deleteById(id);
    }


    public List<BroadcastResponse> getVisibleBroadcasts(String firebaseUid) {

        User user = userRepository.findById(firebaseUid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        List<Broadcast> broadcasts;

        // Admins can see every broadcast.
        if ("admin".equalsIgnoreCase(user.getRole())) {
            broadcasts = broadcastRepository.findAllByOrderByCreatedAtDesc();
        } else {
            String audience = user.getUserType();

            if (audience == null) {
                audience = "none";
            } else {
                audience = audience.trim().toLowerCase();

                if (!List.of("student", "alumni", "faculty")
                        .contains(audience)) {
                    audience = "none";
                }
            }

            broadcasts = broadcastRepository
                    .findByAudienceIgnoreCaseOrAudienceIgnoreCaseOrderByCreatedAtDesc(
                            "all", audience
                    );
        }

        return broadcasts.stream()
                .map(this::mapToResponse)
                .toList();
    }


    public BroadcastResponse getVisibleBroadcastById(
            UUID id, String firebaseUid) {

        User user = userRepository.findById(firebaseUid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Broadcast not found"));

        Broadcast broadcast = broadcastRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Broadcast not found"));

        boolean isAdmin = "admin".equalsIgnoreCase(user.getRole());

        boolean isVisible =
                "all".equalsIgnoreCase(broadcast.getAudience()) ||
                        (user.getUserType() != null &&
                                broadcast.getAudience().equalsIgnoreCase(user.getUserType()));

        if (!isAdmin && !isVisible) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Broadcast not found");
        }

        return mapToResponse(broadcast);
    }


    private BroadcastResponse mapToResponse(Broadcast broadcast) {

        return new BroadcastResponse(
                broadcast.getId(),
                broadcast.getTitle(),
                broadcast.getMessage(),
                broadcast.getImageUrl(),
                broadcast.getLinkUrl(),
                broadcast.getAudience(),
                broadcast.getCreatedBy(),
                broadcast.getCreatedAt(),
                broadcast.getTotalRecipients(),
                broadcast.getTotalOpens(),
                broadcast.getClickCount(),
                broadcast.getLinkClicks()
        );
    }
}
