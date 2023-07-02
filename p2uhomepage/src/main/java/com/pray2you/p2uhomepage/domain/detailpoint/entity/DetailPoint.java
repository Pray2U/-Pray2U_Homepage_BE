package com.pray2you.p2uhomepage.domain.detailpoint.entity;

import com.pray2you.p2uhomepage.domain.totalpoint.entity.TotalPoint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class DetailPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detailpoint_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "totalpoint_id")
    private TotalPoint totalPoint;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private long point;

    @CreatedDate
    private LocalDateTime createDate;


    public DetailPoint(TotalPoint totalPoint, String content, long point) {
        this.totalPoint = totalPoint;
        this.content = content;
        this.point = point;
    }
}
