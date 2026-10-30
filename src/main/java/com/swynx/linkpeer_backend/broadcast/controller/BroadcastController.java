
package com.swynx.linkpeer_backend.broadcast.controller;

import com.swynx.linkpeer_backend.broadcast.dto.request.CreateBroadcastRequest;
import com.swynx.linkpeer_backend.broadcast.dto.response.BroadcastResponse;
import com.swynx.linkpeer_backend.broadcast.service.BroadcastService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PreAuthorize("@broadcastAuthorization.isAdmin(authentication.name)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BroadcastResponse createBroadcast(
            @Valid @RequestBody CreateBroadcastRequest request,
            Authentication authentication) {

        return broadcastService.createBroadcast(
                request,
                authentication.getName()
        );
    }


    @GetMapping
    public List<BroadcastResponse> getAllBroadcasts(
            Authentication authentication) {

        return broadcastService.getVisibleBroadcasts(
                authentication.getName()
        );
    }


    @GetMapping("/{id}")
    public BroadcastResponse getBroadcastById(
            @PathVariable UUID id,
            Authentication authentication) {

        return broadcastService.getVisibleBroadcastById(
                id,
                authentication.getName()
        );
    }


    @PreAuthorize("@broadcastAuthorization.isAdmin(authentication.name)")
    @PutMapping("/{id}")
    public BroadcastResponse updateBroadcast(
            @PathVariable UUID id,
            @Valid @RequestBody CreateBroadcastRequest request) {

        return broadcastService.updateBroadcast(id, request);
    }

    @PreAuthorize("@broadcastAuthorization.isAdmin(authentication.name)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBroadcast(@PathVariable UUID id) {
        broadcastService.deleteBroadcast(id);
    }

}
