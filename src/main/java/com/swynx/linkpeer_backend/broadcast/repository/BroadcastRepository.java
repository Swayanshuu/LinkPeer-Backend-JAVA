package com.swynx.linkpeer_backend.broadcast.repository;

import com.swynx.linkpeer_backend.broadcast.entity.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BroadcastRepository extends JpaRepository<Broadcast, UUID> {

    List<Broadcast> findByAudienceIgnoreCaseOrderByCreatedAtDesc(String audience);

    List<Broadcast> findByAudienceIgnoreCaseOrAudienceIgnoreCaseOrderByCreatedAtDesc(
            String firstAudience,
            String secondAudience
    );
    List<Broadcast> findAllByOrderByCreatedAtDesc();

}
