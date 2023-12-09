package com.pray2you.p2uhomepage.domain.til.dto.response;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class CreateTilResponseDTO {

    private final long tilId;
    private final SimpleUserInfoResponseDTO user;
    private final String tag;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;
    private final int point;

    @Builder
    private CreateTilResponseDTO(
            @NonNull Long tilId,
            @NonNull SimpleUserInfoResponseDTO user,
            String tag,
            @NonNull String title,
            @NonNull String content,
            @NonNull LocalDateTime createdDate,
            int point) {
        this.tilId = tilId;
        this.user = user;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.point = point;
    }

    public static CreateTilResponseDTO toDTO(Til til, int point){
        return CreateTilResponseDTO.builder()
                .tilId(til.getId())
                .user(SimpleUserInfoResponseDTO.toDTO(til.getUser()))
                .tag(til.getTag())
                .title(til.getTitle())
                .content(til.getContent())
                .createdDate(til.getCreatedDate())
                .point(point)
                .build();
    }
}
