package com.pray2you.p2uhomepage.domain.point.dto.response;

import com.pray2you.p2uhomepage.domain.point.entity.Rank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadCurrentRankResponseDTO {

    private final int weekPoint;

    public static ReadCurrentRankResponseDTO toDTO(Rank rank) {
       return new ReadCurrentRankResponseDTO(rank.getWeekPoint());
    }
}
