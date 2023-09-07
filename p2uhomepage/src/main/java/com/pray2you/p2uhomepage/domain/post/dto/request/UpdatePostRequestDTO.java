package com.pray2you.p2uhomepage.domain.post.dto.request;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdatePostRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String fileUrl;

    public UpdatePostRequestDTO(String title, String content, String fileUrl) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
    }

    public Post toEntity(Post post) {
        return post.update(title, content, fileUrl);
    }
}
