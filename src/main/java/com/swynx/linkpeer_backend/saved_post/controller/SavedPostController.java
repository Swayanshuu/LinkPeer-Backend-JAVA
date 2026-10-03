package com.swynx.linkpeer_backend.saved_post.controller;

import com.google.firebase.auth.FirebaseToken;
import com.swynx.linkpeer_backend.saved_post.service.SavedPostService;
import com.swynx.linkpeer_backend.user.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class SavedPostController {

    private final SavedPostService savedPostService;

    public SavedPostController(SavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

    // SAVE POST
    @PostMapping("/{postId}/save")
    public ResponseEntity<Void> savePost(
            @PathVariable Long postId,
            HttpServletRequest httpRequest
    ) {

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        String userId = firebaseUser.getUid();

        savedPostService.savePost(userId, postId);

        return ResponseEntity.ok().build();
    }

    // UNSAVE POST
    @DeleteMapping("/{postId}/save")
    public ResponseEntity<Void> unsavePost(
            @PathVariable Long postId,
            HttpServletRequest httpRequest
    ) {

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        String userId = firebaseUser.getUid();

        savedPostService.unsavePost(userId, postId);

        return ResponseEntity.noContent().build();
    }

    // CHECK SAVED STATUS
    @GetMapping("/{postId}/save")
    public ResponseEntity<Boolean> isPostSaved(
            @PathVariable Long postId,
            HttpServletRequest httpRequest
    ) {

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        String userId = firebaseUser.getUid();

        boolean saved =
                savedPostService.isPostSaved(userId, postId);

        return ResponseEntity.ok(saved);
    }
}