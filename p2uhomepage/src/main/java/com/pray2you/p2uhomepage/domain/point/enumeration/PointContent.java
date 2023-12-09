package com.pray2you.p2uhomepage.domain.point.enumeration;

import lombok.Getter;

@Getter
public enum PointContent {
    TIL("TIL", 80, 120),
    ATTENDANCE("ATTENDANCE", 1, 10),
    COMMIT("COMMIT", 50, 100),
    SEMINAR("SEMINAR", 30, 30),
    ORDER("ORDER", 0, 0);

    final String content;
    final int minPoint;
    final int maxPoint;

    PointContent(String content, int minPoint, int maxPoint) {
        this.content = content;
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }
}
