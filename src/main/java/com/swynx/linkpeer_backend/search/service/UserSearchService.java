package com.swynx.linkpeer_backend.search.service;

import com.swynx.linkpeer_backend.search.dto.response.UserSearchResponse;
import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {

    private final UserRepository userRepository;

    public UserSearchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<UserSearchResponse> searchUsers(
            String name,
            Pageable pageable
    ) {
        return userRepository
                .findByNameContainingIgnoreCaseOrderByNameAsc(name, pageable)
                .map(user -> new UserSearchResponse(
                        user.getId(),
                        user.getName(),
                        user.getPhotoUrl()
                ));
    }

}
