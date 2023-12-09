package com.pray2you.p2uhomepage.domain.point.dto.response;

import com.pray2you.p2uhomepage.domain.point.entity.TotalPoint;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadTotalPointResponseDTO {

    private final long pointId;
    private final long userId;
    private final int currentPoint;

    public ReadTotalPointResponseDTO(long pointId, long userId, int currentPoint) {
        this.pointId = pointId;
        this.userId = userId;
        this.currentPoint = currentPoint;
    }

    public static ReadTotalPointResponseDTO toDTO(TotalPoint totalPoint) {
        return new ReadTotalPointResponseDTO(totalPoint.getId(),
                totalPoint.getUser().getId(),
                totalPoint.getCurrentPoint());
    }
}
