package com.pray2you.p2uhomepage.domain.point.repository;


import com.pray2you.p2uhomepage.domain.point.entity.Rank;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    Optional<Rank> findByUser(User user);

    Optional<Rank> findByUserAndStartDateGreaterThanEqualAndEndDateLessThanEqual(User user, LocalDateTime previousWeek, LocalDateTime previousWeek2);
    Page<Rank> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(Pageable pageable, LocalDateTime previousWeek, LocalDateTime previousWeek2);

}
