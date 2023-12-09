package com.pray2you.p2uhomepage.domain.reply.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.reply.entity.Reply;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class ReadReplyResponseDTO {

    private final long replyId;
    private final long postId;
    private final SimpleUserInfoResponseDTO user;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadReplyResponseDTO(
            @NonNull Long replyId,
            @NonNull Long postId,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull String content,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.replyId = replyId;
        this.postId = postId;
        this.user = user;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadReplyResponseDTO toDTO(Reply reply) {
        return ReadReplyResponseDTO.builder()
                .replyId(reply.getId())
                .postId(reply.getPost().getId())
                .user(SimpleUserInfoResponseDTO.toDTO(reply.getUser()))
                .content(reply.getContent())
                .createdDate(reply.getCreatedDate())
                .modifiedDate(reply.getModifiedDate())
                .build();
    }

}
