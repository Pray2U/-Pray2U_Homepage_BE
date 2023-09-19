package com.pray2you.p2uhomepage.domain.til.dto.response;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateTilResponseDTO {

    private long tilId;
    private long userId;
    private String username;
    private String userImg;
    private String tag;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public CreateTilResponseDTO(long tilId, long userId, String username, String userImg, String tag, String title, String content, LocalDateTime createdDate) {
        this.tilId = tilId;
        this.userId = userId;
        this.username = username;
        this.userImg = userImg;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static CreateTilResponseDTO toDTO(Til til){
        return CreateTilResponseDTO.builder()
                .tilId(til.getId())
                .userId(til.getUser().getId())
                .username(til.getUser().getUsername())
                .userImg(til.getUser().getProfileImgUrl())
                .tag(til.getTag())
                .title(til.getTitle())
                .content(til.getContent())
                .createdDate(til.getCreatedDate())
                .build();
    }
}
