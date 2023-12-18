package com.pray2you.p2uhomepage.domain.point.dto.response;

import com.pray2you.p2uhomepage.domain.point.entity.Rank;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ReadCurrentRankResponseDTO {

    private final long userId;
    private final int weekPoint;

    public ReadCurrentRankResponseDTO(long userId, int weekPoint) {
        this.userId = userId;
        this.weekPoint = weekPoint;
    }

    public static ReadCurrentRankResponseDTO toDTO(User user, Rank rank) {
       return new ReadCurrentRankResponseDTO(user.getId(), rank.getWeekPoint());
    }
}
