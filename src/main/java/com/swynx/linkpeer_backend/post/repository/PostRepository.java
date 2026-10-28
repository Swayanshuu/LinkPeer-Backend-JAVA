package com.swynx.linkpeer_backend.post.repository;

import com.swynx.linkpeer_backend.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByContentContainingIgnoreCaseOrderByCreatedAtDesc(
            String content,
            Pageable pageable
    );

    Page<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(
            String title,
            String content,
            Pageable pageable
    );



}
