
package com.swynx.linkpeer_backend.search.controller;

import com.swynx.linkpeer_backend.search.dto.response.PostSearchResponse;
import com.swynx.linkpeer_backend.search.service.ExploreFeedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/explore")
public class ExploreFeedController {

    private final ExploreFeedService exploreFeedService;

    public ExploreFeedController(ExploreFeedService exploreFeedService) {
        this.exploreFeedService = exploreFeedService;
    }


    @GetMapping
    public Page<PostSearchResponse> getExploreFeed(
            @RequestParam(required = false) String postType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (page < 0 || size < 1 || size > 50) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be >= 0 and size must be between 1 and 50"
            );
        }

        Pageable pageable = PageRequest.of(page, size);

        if (postType == null || postType.isBlank()) {
            return exploreFeedService.getExploreFeed(pageable);
        }

        if (!java.util.Set.of(
                "normal", "announcement", "internship", "job"
        ).contains(postType.toLowerCase(java.util.Locale.ROOT))) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid postType. Allowed values: normal, announcement, internship, job"
            );
        }

        return exploreFeedService.getExploreFeedByType(
                postType, pageable
        );
    }

}

