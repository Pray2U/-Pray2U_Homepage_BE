package com.pray2you.p2uhomepage.domain.point.repository;

import com.pray2you.p2uhomepage.domain.point.entity.TotalPoint;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TotalPointRepository extends JpaRepository<TotalPoint, Long> {

    Optional<TotalPoint> findByUser(User user);
    boolean existsByUser(User user);
}
