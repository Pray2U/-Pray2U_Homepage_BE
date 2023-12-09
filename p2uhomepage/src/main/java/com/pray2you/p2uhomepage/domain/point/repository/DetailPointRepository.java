package com.pray2you.p2uhomepage.domain.point.repository;

import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetailPointRepository extends JpaRepository<DetailPoint, Long> {
    List<DetailPoint> findAllByUser(User user);
    List<DetailPoint> findAllByUserAndContentAndCreateDateBetween(User user, PointContent content, LocalDateTime weekOfMonday, LocalDateTime weekOfNextMonday);
}
