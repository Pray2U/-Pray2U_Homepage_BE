package com.pray2you.p2uhomepage.domain.point.event;

import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointAddEvent implements PointEvent{

    private PointContent content;
    private int point;
    private User user;

    public DetailPoint toDetailPoint() {
        return new DetailPoint(user, content, point);
    }
}
