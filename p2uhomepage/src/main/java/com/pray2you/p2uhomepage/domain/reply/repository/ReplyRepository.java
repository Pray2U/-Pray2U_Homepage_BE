package com.pray2you.p2uhomepage.domain.reply.repository;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.reply.entity.Reply;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Optional<Reply> findByIdAndDeleted(long replyId, boolean deleted);
    Optional<Reply> findByIdAndUserAndDeleted(long replyId, User user, boolean deleted);
    Page<Reply> findAllByPostAndDeleted(Pageable pageable, Post post, boolean deleted);
}
