package com.pray2you.p2uhomepage.domain.post.dto.request;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePostRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
    
    private String fileUrl;

    public UpdatePostRequestDTO(String title, String content, String fileUrl) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
    }

    public Post toEntity(Post post) {
        post.update(title, content, fileUrl);
        return post;
    }
}
