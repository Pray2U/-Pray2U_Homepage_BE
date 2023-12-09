package com.pray2you.p2uhomepage.domain.til.repository;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TilRepository extends JpaRepository<Til, Long> {

    List<Til> findByUserAndCreatedDateGreaterThanEqual(User user, LocalDateTime localDateTime);
    List<Til> findAllByUserAndCreatedDateGreaterThanEqualAndDeleted(User user, LocalDateTime thisMonday, boolean deleted);
    Til findOneByUserAndCreatedDateGreaterThanEqualAndDeleted(User user, LocalDateTime thisMonday, boolean deleted);

    Optional<Til> findByIdAndDeleted(long tilId, boolean deleted);
    Optional<Til> findByIdAndUserAndDeleted(long tilId, User user, boolean deleted);
    Page<Til> findAllByDeleted(Pageable pageable, boolean deleted);
    Page<Til> findAllByUserAndDeleted(Pageable pageable, User user, boolean deleted);

    @Query(
            value = "SELECT * FROM til WHERE (title LIKE '%:keyword%' OR content LIKE '%:keyword%' OR tag LIKE '%:keyword%') AND deleted = false",
            countQuery = "SELECT COUNT(*) FROM til",
            nativeQuery = true
    )
    Page<Til> findAllByTitleContainingOrContentContainingOrTagContaining(Pageable pageable, @RequestParam("keyword")String keyword);
}
