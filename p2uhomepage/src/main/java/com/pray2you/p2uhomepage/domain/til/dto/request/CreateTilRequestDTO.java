package com.pray2you.p2uhomepage.domain.til.dto.request;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTilRequestDTO {

    @NotBlank
    private String title;

    @Pattern(regexp = "([ㄱ-ㅎ가-힣a-zA-Z0-9 ]{2,10},){0,9}([ㄱ-ㅎ가-힣a-zA-Z0-9 ]{2,10})", message = "각 태그가 쉼표로 구분이 되어 있지 않거나, 태그의 개수가 10개 초과하거나, 각 태그의 단어가 2-10글자를 만족하는지 확인하세요.")
    private String tag;

    @NotBlank
    private String content;

    public CreateTilRequestDTO(String title, String tag, String content) {
        this.title = title;
        this.tag = tag;
        this.content = content;
    }

    public Til toEntity(User user) {
        return Til.builder()
                .user(user)
                .title(title)
                .content(content)
                .tag(tag)
                .build();
    }
}
