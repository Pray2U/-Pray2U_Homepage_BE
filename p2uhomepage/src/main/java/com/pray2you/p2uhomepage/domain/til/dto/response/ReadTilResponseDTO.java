package com.pray2you.p2uhomepage.domain.til.dto.response;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class ReadTilResponseDTO {

    private final long tilId;
    private final String tag;
    private final String title;
    private final String content;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadTilResponseDTO(
            @NonNull Long tilId,
            String tag,
            @NonNull String title,
            @NonNull String content,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.tilId = tilId;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadTilResponseDTO toDTO(Til til) {
        return ReadTilResponseDTO.builder()
                .tilId(til.getId())
                .user(SimpleUserInfoResponseDTO.toDTO(til.getUser()))
                .tag(til.getTag())
                .title(til.getTitle())
                .content(til.getContent())
                .createdDate(til.getCreatedDate())
                .modifiedDate(til.getModifiedDate())
                .build();
    }
}
