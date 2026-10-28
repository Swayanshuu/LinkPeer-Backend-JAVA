
package com.swynx.linkpeer_backend.search.service;

import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.search.dto.response.PostSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostSearchService {

    private final PostRepository postRepository;

    public PostSearchService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<PostSearchResponse> searchPosts(
            String content,
            Pageable pageable
    ) {
        return postRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(
                        content,
                        content,
                        pageable
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
