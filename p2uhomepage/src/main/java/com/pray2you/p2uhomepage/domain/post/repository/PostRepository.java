package com.pray2you.p2uhomepage.domain.post.repository;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndDeleted(long postId, boolean deleted);
    Optional<Post> findByIdAndUserAndDeleted(long postId, User user, boolean deleted);
    Page<Post> findByDeleted(Pageable pageable, boolean deleted);
}
