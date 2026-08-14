package com.swynx.linkpeer_backend.post.dto.response;

import java.util.List;

public class PostPageResponse {

    private List<PostResponse> posts;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalPosts;
    private boolean hasNext;

    public PostPageResponse(
            List<PostResponse> posts,
            int currentPage,
            int pageSize,
            int totalPages,
            long totalPosts,
            boolean hasNext) {

        this.posts = posts;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalPosts = totalPosts;
        this.hasNext = hasNext;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalPosts() {
        return totalPosts;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}