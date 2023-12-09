package com.pray2you.p2uhomepage.domain.point.event;

import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.user.entity.User;

public interface PointEvent {
    User getUser();
    int getPoint();
    PointContent getContent();
    DetailPoint toDetailPoint();
}
