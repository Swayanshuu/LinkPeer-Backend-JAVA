package com.swynx.linkpeer_backend.search.controller;

import com.swynx.linkpeer_backend.search.dto.response.UserSearchResponse;
import com.swynx.linkpeer_backend.search.service.UserSearchService;
import com.swynx.linkpeer_backend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/search")
public class UserSearchController {

    private final UserSearchService userSearchService;
    @Autowired
    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }



    @GetMapping("/users")
    public Page<UserSearchResponse> searchUsers(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Search name cannot be blank"
            );
        }
        if (page < 0 || size < 1 || size > 50) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be >= 0 and size must be between 1 and 50"
            );
        }

        Pageable pageable = PageRequest.of(page, size);
        return userSearchService.searchUsers(name, pageable);
    }


}
