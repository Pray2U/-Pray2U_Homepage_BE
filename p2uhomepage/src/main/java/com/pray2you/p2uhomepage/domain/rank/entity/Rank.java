package com.pray2you.p2uhomepage.domain.rank.entity;

import com.pray2you.p2uhomepage.domain.model.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "`rank`")
public class Rank extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private long id;

    @Column(nullable = false)
    private long weekPoint;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int ranking;

    public Rank(long weekPoint, LocalDateTime startDate, LocalDateTime endDate, int ranking) {
        this.weekPoint = weekPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ranking = ranking;
    }
}
