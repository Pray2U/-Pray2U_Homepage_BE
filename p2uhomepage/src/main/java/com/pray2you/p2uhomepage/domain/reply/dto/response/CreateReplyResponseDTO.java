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
public class CreateReplyResponseDTO {

    private final long replyId;
    private final long postId;
    private final String content;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createdDate;

    @Builder
    private CreateReplyResponseDTO(
            @NonNull Long replyId,
            @NonNull Long postId,
            @NonNull String content,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull LocalDateTime createdDate) {
        this.replyId = replyId;
        this.postId = postId;
        this.content = content;
        this.user = user;
        this.createdDate = createdDate;
    }

    public static CreateReplyResponseDTO toDTO(Reply reply) {
        return CreateReplyResponseDTO.builder()
                .replyId(reply.getId())
                .postId(reply.getPost().getId())
                .content(reply.getContent())
                .user(SimpleUserInfoResponseDTO.toDTO(reply.getUser()))
                .createdDate(reply.getCreatedDate())
                .build();
    }
}
