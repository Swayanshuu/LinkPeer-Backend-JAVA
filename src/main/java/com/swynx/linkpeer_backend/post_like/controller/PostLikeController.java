package com.swynx.linkpeer_backend.post_like.controller;

import com.google.firebase.auth.FirebaseToken;
import com.swynx.linkpeer_backend.post_like.dto.response.PostLikeStatusResponse;
import com.swynx.linkpeer_backend.post_like.service.PostLikeService;
import com.swynx.linkpeer_backend.user.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    // LIKE POST
    @PostMapping
    public ResponseEntity<Void> likePost(
            @PathVariable Long postId,
            HttpServletRequest httpRequest) {

        // Get authenticated Firebase user from our authentication filter
        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        // User must be authenticated to like a post
        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        // Get the Firebase UID of the current user
        String userId = firebaseUser.getUid();

        // Like the post
        postLikeService.likePost(postId, userId);

        return ResponseEntity.ok().build();
    }


    // UNLIKE POST
    @DeleteMapping
    public ResponseEntity<Void> unlikePost(
            @PathVariable Long postId,
            HttpServletRequest httpRequest) {

        // Get authenticated Firebase user
        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        // User must be authenticated
        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        // Get current user's Firebase UID
        String userId = firebaseUser.getUid();

        // Remove the like
        postLikeService.unlikePost(postId, userId);

        return ResponseEntity.noContent().build();
    }

    // GET HAS THE USER LIKED THE POST OR NOT
    @GetMapping
    public ResponseEntity<PostLikeStatusResponse> getLikeStatus(
            @PathVariable Long postId,
            HttpServletRequest httpRequest) {

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        String userId = firebaseUser.getUid();

        boolean liked = postLikeService.hasUserLikedPost(
                postId,
                userId
        );

        return ResponseEntity.ok(
                new PostLikeStatusResponse(liked)
        );
    }
}

