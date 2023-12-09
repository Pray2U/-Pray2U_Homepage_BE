package com.pray2you.p2uhomepage.domain.til.repository;

import com.pray2you.p2uhomepage.domain.til.entity.TilRandomPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TilRandomPointRepository extends JpaRepository<TilRandomPoint, Long> {
}
