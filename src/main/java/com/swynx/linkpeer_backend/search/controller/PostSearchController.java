
package com.swynx.linkpeer_backend.search.controller;

import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.search.dto.response.PostSearchResponse;
import com.swynx.linkpeer_backend.search.service.PostSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/search")
public class PostSearchController {

    private final PostSearchService postSearchService;

    public PostSearchController(PostSearchService postSearchService) {
        this.postSearchService = postSearchService;
    }

    @GetMapping("/posts")
    public Page<PostSearchResponse> searchPosts(
            @RequestParam String content,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        if (content == null || content.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Search content cannot be blank"
            );
        }

        if (page < 0 || size < 1 || size > 50) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be >= 0 and size must be between 1 and 50"
            );
        }

        Pageable pageable = PageRequest.of(page, size);
        return postSearchService.searchPosts(content, pageable);
    }
}
