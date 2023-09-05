package com.pray2you.p2uhomepage.domain.user.repository;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByGithubId(String githubId);
    Optional<User> findByGithubIdAndDeleted(String githubId, boolean deleted);

    Optional<User> findByIdAndDeleted(Long id, boolean deleted);
    Page<User> findByDeleted(Pageable pageable, boolean deleted);
}
