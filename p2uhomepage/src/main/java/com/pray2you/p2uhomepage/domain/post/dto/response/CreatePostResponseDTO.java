package com.pray2you.p2uhomepage.domain.post.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostResponseDTO {

    private long postId;
    private String title;
    private long writerId;
    private String writerName;
    private String content;
    private String fileUrl;
    private LocalDateTime createDate;

    @Builder
    public CreatePostResponseDTO(long postId, String title, long writerId, String writerName, String content, String fileUrl, LocalDateTime createDate) {
        this.postId = postId;
        this.title = title;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.fileUrl = fileUrl;
        this.createDate = createDate;
    }

    public static CreatePostResponseDTO toDTO(Post post) {
        return CreatePostResponseDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .writerId(post.getUser().getId())
                .writerName(post.getUser().getUsername())
                .content(post.getContent())
                .fileUrl(post.getFileUrl())
                .createDate(post.getCreatedDate())
                .build();
    }
}
