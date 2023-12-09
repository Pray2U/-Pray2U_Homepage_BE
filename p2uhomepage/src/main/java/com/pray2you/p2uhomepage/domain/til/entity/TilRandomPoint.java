package com.pray2you.p2uhomepage.domain.til.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class TilRandomPoint {

    @Id
    private long id;

    @Column(nullable = false)
    private int randomPoint1;

    @Column(nullable = false)
    private int randomPoint2;

    public TilRandomPoint(long id, int randomPoint1, int randomPoint2) {
        this.id = id;
        this.randomPoint1 = randomPoint1;
        this.randomPoint2 = randomPoint2;
    }
}
