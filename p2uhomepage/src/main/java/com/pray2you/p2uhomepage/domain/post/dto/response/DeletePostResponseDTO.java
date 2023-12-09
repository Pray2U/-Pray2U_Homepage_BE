package com.pray2you.p2uhomepage.domain.post.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class DeletePostResponseDTO {

    private final long postId;
    private final String title;
    private final String content;
    private final String fileUrl;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createDate;
    private final LocalDateTime deletedDate;

    @Builder
    private DeletePostResponseDTO(
            @NonNull Long postId,
            @NonNull String title,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull String content,
            String fileUrl,
            @NonNull LocalDateTime createDate,
            @NonNull LocalDateTime deletedDate) {
        this.postId = postId;
        this.title = title;
        this.user = user;
        this.content = content;
        this.fileUrl = fileUrl;
        this.createDate = createDate;
        this.deletedDate = deletedDate;
    }



    public static DeletePostResponseDTO toDTO(Post post) {
        return DeletePostResponseDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .user(SimpleUserInfoResponseDTO.toDTO(post.getUser()))
                .fileUrl(post.getFileUrl())
                .createDate(post.getCreatedDate())
                .deletedDate(post.getModifiedDate())
                .build();
    }
}
