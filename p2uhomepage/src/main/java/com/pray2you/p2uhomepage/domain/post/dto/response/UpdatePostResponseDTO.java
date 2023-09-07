package com.pray2you.p2uhomepage.domain.post.dto.response;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostResponseDTO {

    private long postId;
    private String title;
    private long writerId;
    private String writerName;
    private String content;
    private String fileUrl;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @Builder
    public UpdatePostResponseDTO(long postId, String title, long writerId, String writerName, String content, String fileUrl, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.postId = postId;
        this.title = title;
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.fileUrl = fileUrl;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static UpdatePostResponseDTO toDTO(Post post) {
        return UpdatePostResponseDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .writerId(post.getUser().getId())
                .writerName(post.getUser().getUsername())
                .content(post.getContent())
                .fileUrl(post.getFileUrl())
                .createDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }

}
