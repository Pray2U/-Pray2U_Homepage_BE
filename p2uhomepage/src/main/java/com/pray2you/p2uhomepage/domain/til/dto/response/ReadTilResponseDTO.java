package com.pray2you.p2uhomepage.domain.til.dto.response;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadTilResponseDTO {

    private long tilId;
    private long userId;
    private String username;
    private String userImg;
    private String tag;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReadTilResponseDTO(long tilId, long userId, String username, String userImg, String tag, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.tilId = tilId;
        this.userId = userId;
        this.username = username;
        this.userImg = userImg;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadTilResponseDTO toDTO(Til til) {
        return ReadTilResponseDTO.builder()
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
