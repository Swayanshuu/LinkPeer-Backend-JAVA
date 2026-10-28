
package com.swynx.linkpeer_backend.search.controller;

import com.swynx.linkpeer_backend.search.dto.response.UnifiedSearchResponse;
import com.swynx.linkpeer_backend.search.service.UnifiedSearchService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/search")
public class UnifiedSearchController {

    private final UnifiedSearchService unifiedSearchService;

    public UnifiedSearchController(
            UnifiedSearchService unifiedSearchService
    ) {
        this.unifiedSearchService = unifiedSearchService;
    }

    @GetMapping
    public UnifiedSearchResponse search(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (query.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Search query cannot be blank"
            );
        }

        if (page < 0 || size < 1 || size > 50) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be >= 0 and size must be between 1 and 50"
            );
        }

        return unifiedSearchService.search(query, page, size);
    }
}
