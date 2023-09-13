package com.pray2you.p2uhomepage.domain.reply.dto.request;

import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.reply.entity.Reply;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateReplyRequestDTO {

    @NotBlank
    private String content;

    public CreateReplyRequestDTO(String content) {
        this.content = content;
    }

    public Reply toEntity(User user, Post post) {
        return new Reply(post, user, content);
    }
}
