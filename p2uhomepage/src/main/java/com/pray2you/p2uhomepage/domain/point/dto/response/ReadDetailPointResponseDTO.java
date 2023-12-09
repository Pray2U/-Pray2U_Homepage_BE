package com.pray2you.p2uhomepage.domain.point.dto.response;


import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ReadDetailPointResponseDTO {

    private final long detailPointId;
    private final long userId;
    private final PointContent content;
    private final int point;
    private final LocalDateTime createdDate;

    @Builder
    private ReadDetailPointResponseDTO(
            @NonNull Long detailPointId,
            @NonNull Long userId,
            @NonNull PointContent content,
            @NonNull Integer point,
            @NonNull LocalDateTime createdDate) {
        this.detailPointId = detailPointId;
        this.userId = userId;
        this.content = content;
        this.point = point;
        this.createdDate = createdDate;
    }

    public static ReadDetailPointResponseDTO toDTO(DetailPoint detailPoint) {
        return ReadDetailPointResponseDTO.builder()
                .detailPointId(detailPoint.getId())
                .userId(detailPoint.getUser().getId())
                .content(detailPoint.getContent())
                .point(detailPoint.getPoint())
                .createdDate(detailPoint.getCreateDate())
                .build();
    }
}
