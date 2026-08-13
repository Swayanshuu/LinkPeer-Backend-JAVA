package com.swynx.linkpeer_backend.post.controller;

import com.google.firebase.auth.FirebaseToken;
import com.swynx.linkpeer_backend.post.dto.request.PostCreateRequest;
import com.swynx.linkpeer_backend.post.dto.response.PostResponse;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.mapper.PostMapper;
import com.swynx.linkpeer_backend.post.service.PostService;
import com.swynx.linkpeer_backend.user.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(
            PostService postService,
            PostMapper postMapper) {

        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @Valid @RequestBody PostCreateRequest request,
            HttpServletRequest httpRequest) {

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");
        }

        String userId = firebaseUser.getUid();

        Post post = postMapper.toEntity(request);

        Post createdPost = postService.createPost(userId, post);

        return ResponseEntity.ok(
                postMapper.toResponse(createdPost)
        );
    }


    // get all post
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {

        List<Post> posts = postService.getAllPosts();

        List<PostResponse> responses = posts.stream()
                .map(postMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    // here we use '?' insted of PostResponse/SelfPostResponse because we want diff responses according to who is requesting it
    public ResponseEntity<?> getPostById(
            @PathVariable Long id, HttpServletRequest httpRequest) {

        Post post = postService.getPostById(id);

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser == null) {
            return ResponseEntity.ok(
                    postMapper.toResponse(post)
            );
        }

        String userId = firebaseUser.getUid();

        if(userId == null || !userId.equals(post.getUserId())) {
            return ResponseEntity.ok(
                    postMapper.toResponse(post)
            );
        }else{
            return ResponseEntity.ok(
                    postMapper.toSelfResponse(post)
            );
        }

    }
}