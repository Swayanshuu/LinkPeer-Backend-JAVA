package com.swynx.linkpeer_backend.comment.controller;

import com.google.firebase.auth.FirebaseToken;
import com.swynx.linkpeer_backend.comment.dto.response.CommentResponse;
import com.swynx.linkpeer_backend.comment.mapper.CommentMapper;
import com.swynx.linkpeer_backend.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import com.swynx.linkpeer_backend.comment.dto.request.CommentCreateRequest;
import com.swynx.linkpeer_backend.comment.entity.Comment;
import com.swynx.linkpeer_backend.user.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
        this.commentMapper = new CommentMapper();
    }


    // CREATE COMMENT
    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentCreateRequest request,
            HttpServletRequest httpRequest
    ) {

        // Get authenticated Firebase user from the request
        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        // User must be authenticated to create a comment
        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        // Get Firebase UID
        String userId = firebaseUser.getUid();

        // Convert request DTO → Comment entity
        Comment comment = commentMapper.toEntity(request);

        // Create and save the comment
        Comment createdComment =
                commentService.createComment(userId, postId, comment);

        // Convert entity → response DTO
        return ResponseEntity.ok(
                commentMapper.toResponse(createdComment)
        );
    }

    // GET COMMENT
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId(
            @PathVariable Long postId
    ) {

        // Get all comments belonging to this post
        List<Comment> comments =
                commentService.getCommentsByPostId(postId);

        // Convert every Comment entity → CommentResponse
        List<CommentResponse> responses = comments.stream()
                .map(commentMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }


    // UPDATE COMMENT
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentCreateRequest request,
            HttpServletRequest httpRequest
    ) {

        // Get authenticated Firebase user
        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        // User must be authenticated
        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        // Get current user's Firebase UID
        String userId = firebaseUser.getUid();

        // Convert request DTO
        Comment comment = commentMapper.toEntity(request);

        // Update the comment
        Comment updatedComment =
                commentService.updateComment(
                        userId,
                        commentId,
                        comment
                );

        // Convert updated entity → response DTO
        return ResponseEntity.ok(
                commentMapper.toResponse(updatedComment)
        );
    }

    // DELETE COMMENT
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            HttpServletRequest httpRequest
    ) {

        // Get authenticated Firebase user
        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        // User must be authenticated
        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        // Get current user's Firebase UID
        String userId = firebaseUser.getUid();

        // Delete the comment
        commentService.deleteComment(userId, commentId);

        // Return 204 No Content
        return ResponseEntity.noContent().build();
    }
}
