package com.pray2you.p2uhomepage.domain.reply.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.reply.entity.Reply;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadReplyResponseDTO {

    private long replyId;
    private long postId;
    private long userId;
    private String username;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReadReplyResponseDTO(long replyId, long postId, long userId, String username, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.replyId = replyId;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadReplyResponseDTO toDTO(Reply reply) {
        return ReadReplyResponseDTO.builder()
                .replyId(reply.getId())
                .postId(reply.getPost().getId())
                .userId(reply.getUser().getId())
                .username(reply.getUser().getUsername())
                .content(reply.getContent())
                .createdDate(reply.getCreatedDate())
                .modifiedDate(reply.getModifiedDate())
                .build();
    }
}
