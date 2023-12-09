package com.pray2you.p2uhomepage.global.utils;

import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;

public class RandomPointUtil {
    public static int getRandomPoint(PointContent pointContent) {
        long min = pointContent.getMinPoint();
        long max = pointContent.getMaxPoint();

        return (int)((Math.random() * max - min) + min);
    }
}
