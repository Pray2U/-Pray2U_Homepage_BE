package com.pray2you.p2uhomepage.domain.post.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class ReadAllPostResponseDTO {

    private final long postId;
    private final String title;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadAllPostResponseDTO(
            @NonNull Long postId,
            @NonNull String title,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull LocalDateTime createDate,
            @NonNull LocalDateTime modifiedDate) {
        this.postId = postId;
        this.title = title;
        this.user = user;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadAllPostResponseDTO toDTO(Post post) {
        return ReadAllPostResponseDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .user(SimpleUserInfoResponseDTO.toDTO(post.getUser()))
                .createDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
