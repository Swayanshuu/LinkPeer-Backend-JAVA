
package com.swynx.linkpeer_backend.search.service;

import com.swynx.linkpeer_backend.search.dto.response.UnifiedSearchResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UnifiedSearchService {

    private final UserSearchService userSearchService;
    private final PostSearchService postSearchService;

    public UnifiedSearchService(
            UserSearchService userSearchService,
            PostSearchService postSearchService
    ) {
        this.userSearchService = userSearchService;
        this.postSearchService = postSearchService;
    }

    public UnifiedSearchResponse search(
            String query,
            int page,
            int size
    ) {
        var pageable = PageRequest.of(page, size);

        var users = userSearchService.searchUsers(query, pageable);
        var posts = postSearchService.searchPosts(query, pageable);

        return new UnifiedSearchResponse(users, posts);
    }
}
