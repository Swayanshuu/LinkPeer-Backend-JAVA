
package com.swynx.linkpeer_backend.broadcast.security;

import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("broadcastAuthorization")
public class BroadcastAuthorization {

    private final UserRepository userRepository;

    public BroadcastAuthorization(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean isAdmin(String firebaseUid) {

        return userRepository.findById(firebaseUid)
                .map(user -> "admin".equalsIgnoreCase(user.getRole()))
                .orElse(false);
    }

}
