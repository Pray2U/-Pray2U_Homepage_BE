package com.pray2you.p2uhomepage.domain.post.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class CreatePostResponseDTO {

    private final long postId;
    private final String title;
    private final String content;
    private final String fileUrl;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createDate;

    @Builder
    private CreatePostResponseDTO(
            @NonNull Long postId,
            @NonNull String title,
            @NonNull String content,
            String fileUrl,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull LocalDateTime createDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
        this.user = user;
        this.createDate = createDate;
    }

    public static CreatePostResponseDTO toDTO(Post post) {
        return CreatePostResponseDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .fileUrl(post.getFileUrl())
                .user(SimpleUserInfoResponseDTO.toDTO(post.getUser()))
                .createDate(post.getCreatedDate())
                .build();
    }
}
