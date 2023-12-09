package com.pray2you.p2uhomepage.domain.post.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class UpdatePostResponseDTO {

    private final long postId;
    private final String title;
    private final String content;
    private final String fileUrl;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private UpdatePostResponseDTO(
            @NonNull Long postId,
            @NonNull String title,
            @NonNull String content,
            String fileUrl,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull LocalDateTime createDate,
            @NonNull LocalDateTime modifiedDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
        this.user = user;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }



    public static UpdatePostResponseDTO toDTO(Post post) {
        return UpdatePostResponseDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .user(SimpleUserInfoResponseDTO.toDTO(post.getUser()))
                .fileUrl(post.getFileUrl())
                .createDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }

}
