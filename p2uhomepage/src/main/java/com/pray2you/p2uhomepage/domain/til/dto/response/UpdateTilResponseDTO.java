package com.pray2you.p2uhomepage.domain.til.dto.response;

import com.pray2you.p2uhomepage.domain.post.dto.response.CreatePostResponseDTO;
import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class UpdateTilResponseDTO {

    private final long tilId;
    private final SimpleUserInfoResponseDTO user;
    private final String tag;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private UpdateTilResponseDTO(
            @NonNull Long tilId,
            @NonNull SimpleUserInfoResponseDTO user,
            String tag,
            @NonNull String title,
            @NonNull String content,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.tilId = tilId;
        this.user = user;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static UpdateTilResponseDTO toDTO(Til til) {
        return UpdateTilResponseDTO.builder()
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
