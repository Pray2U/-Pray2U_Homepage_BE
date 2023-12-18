package com.pray2you.p2uhomepage.domain.point.dto.response;

import com.pray2you.p2uhomepage.domain.point.entity.Rank;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ReadRankResponseDTO {

    private final long userId;
    private final int weekPoint;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final long ranking;

    @Builder
    private ReadRankResponseDTO(
            @NonNull long userId,
            @NonNull int weekPoint,
            @NonNull LocalDateTime startDate,
            @NonNull LocalDateTime endDate,
            @NonNull long ranking) {
        this.userId = userId;
        this.weekPoint = weekPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ranking = ranking;
    }

    public static ReadRankResponseDTO toDTO(Rank rank, User user) {
        return builder()
                .userId(user.getId())
                .weekPoint(rank.getWeekPoint())
                .startDate(rank.getStartDate())
                .endDate(rank.getEndDate())
                .ranking(rank.getRanking())
                .build();
    }
}
