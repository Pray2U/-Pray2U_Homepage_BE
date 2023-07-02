package com.pray2you.p2uhomepage.domain.item.entity;

import com.pray2you.p2uhomepage.domain.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private long point;

    @Column(nullable = false)
    private String itemDescription;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public Item(String itemName, String imgUrl, long point, String itemDescription) {
        this.itemName = itemName;
        this.imgUrl = imgUrl;
        this.point = point;
        this.itemDescription = itemDescription;
    }
}
