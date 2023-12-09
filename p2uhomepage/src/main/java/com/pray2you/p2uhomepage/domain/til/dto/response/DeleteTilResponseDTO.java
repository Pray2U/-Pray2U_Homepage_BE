package com.pray2you.p2uhomepage.domain.til.dto.response;

import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class DeleteTilResponseDTO {

    private final long tilId;
    private final SimpleUserInfoResponseDTO user;
    private final String tag;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime deletedDate;
    private final int point;

    @Builder
    private DeleteTilResponseDTO(
            @NonNull Long tilId,
            @NonNull SimpleUserInfoResponseDTO user,
            String tag,
            @NonNull String title,
            @NonNull String content,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime deletedDate,
            @NonNull Integer point) {
        this.tilId = tilId;
        this.user = user;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
        this.point = point;
    }

    public static DeleteTilResponseDTO toDTO(Til til, int point) {
        return DeleteTilResponseDTO.builder()
                .tilId(til.getId())
                .user(SimpleUserInfoResponseDTO.toDTO(til.getUser()))
                .tag(til.getTag())
                .title(til.getTitle())
                .content(til.getContent())
                .createdDate(til.getCreatedDate())
                .deletedDate(til.getModifiedDate())
                .point(point)
                .build();
    }
}
