package com.pray2you.p2uhomepage.domain.point.entity;

import com.pray2you.p2uhomepage.global.config.BaseTimeEntity;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "`rank`")
public class Rank extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int weekPoint;

    @Column
    private int ranking;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Builder
    private Rank(
            @NonNull User user,
            @NonNull Integer weekPoint,
            @NonNull LocalDateTime startDate,
            @NonNull LocalDateTime endDate) {
        this.user = user;
        this.weekPoint = weekPoint;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void updatePoint(long point) {
        this.weekPoint += point;
    }
}
