package com.pray2you.p2uhomepage.domain.point.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TotalPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "totalpoint_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int currentPoint = 0;

    public TotalPoint(User user) {
        this.user = user;
    }

    public void addPoint(long point) {
        this.currentPoint += point;
        if(currentPoint < 0) {
            throw new RestApiException(UserErrorCode.NO_POINT_EXCEPTION);
        }
    }
}
