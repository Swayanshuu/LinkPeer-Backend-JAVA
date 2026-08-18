package com.swynx.linkpeer_backend.post.controller;

import com.google.firebase.auth.FirebaseToken;
import com.swynx.linkpeer_backend.post.dto.request.PostCreateRequest;
import com.swynx.linkpeer_backend.post.dto.request.PostUpdateRequest;
import com.swynx.linkpeer_backend.post.dto.response.PostPageResponse;
import com.swynx.linkpeer_backend.post.dto.response.PostResponse;
import com.swynx.linkpeer_backend.post.dto.response.SelfPostResponse;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.exception.InvalidPaginationException;
import com.swynx.linkpeer_backend.post.mapper.PostMapper;
import com.swynx.linkpeer_backend.post.service.PostService;
import com.swynx.linkpeer_backend.user.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<PostPageResponse> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {

        // adding limit

        // page can't be negative
        if (page < 0) {
            throw new InvalidPaginationException("Page can't be negative");
        }

        // and a page can't content more than 10 posts
        if (size < 1 || size > 10) {
            throw new InvalidPaginationException("Size must be between 1 and 10");
        }

        // by default it will sort by created at in desc order
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        // added pagination
        Page<Post> posts = postService.getAllPosts(pageable);

        List<PostResponse> responses = posts.stream()
                .map(postMapper::toResponse)
                .toList();

        PostPageResponse response = new PostPageResponse(
                responses,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalPages(),
                posts.getTotalElements(),
                posts.hasNext()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    // here we use '?' instead of PostResponse/SelfPostResponse because we want diff responses according to who is requesting it
    public ResponseEntity<?> getPostById(
            @PathVariable Long id, HttpServletRequest httpRequest) {

        Post post = postService.getPostById(id);

        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");

        if (firebaseUser != null &&
                firebaseUser.getUid().equals(post.getUserId())) {

            return ResponseEntity.ok(
                    postMapper.toSelfResponse(post)
            );
        }

        return ResponseEntity.ok(
                postMapper.toResponse(post)
        );

    }

    // EDIT POST
    @PutMapping("/{id}")
    public ResponseEntity<SelfPostResponse> updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateRequest request, HttpServletRequest httpRequest) {
        FirebaseToken firebaseUser =
                (FirebaseToken) httpRequest.getAttribute("firebaseUser");
        if (firebaseUser == null) {
            throw new UnauthorizedException("Authentication required");

        }
        String userId = firebaseUser.getUid();

        Post updatePost = postService.updatePost(userId, id, request);

        return ResponseEntity.ok(postMapper.toSelfResponse(updatePost));
    }
}