package com.pray2you.p2uhomepage.domain.totalpoint.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
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
    private long totalPoint;

    @Column(nullable = false)
    private long currentPoint;

    public TotalPoint(User user, long totalPoint, long currentPoint) {
        this.user = user;
        this.totalPoint = totalPoint;
        this.currentPoint = currentPoint;
    }
}
