
package com.swynx.linkpeer_backend.search.service;

import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.search.dto.response.PostSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExploreFeedService {

    private final PostRepository postRepository;

    public ExploreFeedService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<PostSearchResponse> getExploreFeed(Pageable pageable) {
        return postRepository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(post -> new PostSearchResponse(
                        post.getId(),
                        post.getContent(),
                        post.getUserId(),
                        post.getUserName(),
                        post.getUserPhoto(),
                        post.getPostType(),
                        post.getTitle(),
                        post.getImageUrl(),
                        post.getCreatedAt()
                ));
    }


    public Page<PostSearchResponse> getExploreFeedByType(
            String postType,
            Pageable pageable
    ) {
        return postRepository
                .findByPostTypeIgnoreCaseOrderByCreatedAtDesc(
                        postType, pageable
                )
                .map(post -> new PostSearchResponse(
                        post.getId(),
                        post.getContent(),
                        post.getUserId(),
                        post.getUserName(),
                        post.getUserPhoto(),
                        post.getPostType(),
                        post.getTitle(),
                        post.getImageUrl(),
                        post.getCreatedAt()
                ));
    }

}
