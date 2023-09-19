package com.pray2you.p2uhomepage.domain.til.repository;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TilRepository extends JpaRepository<Til, Long> {

    Optional<Til> findByIdAndDeleted(long tilId, boolean deleted);
    Page<Til> findAllByDeleted(Pageable pageable, boolean deleted);
    Page<Til> findAllByUserAndDeleted(Pageable pageable, User user, boolean deleted);
}
