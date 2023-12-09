package com.pray2you.p2uhomepage.domain.item.entity;

import com.pray2you.p2uhomepage.global.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private int point;

    @Column(nullable = false)
    private String itemDescription;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    private Item(
            @NonNull String itemName,
            @NonNull String imgUrl,
            @NonNull Integer point,
            @NonNull String itemDescription) {
        this.itemName = itemName;
        this.imgUrl = imgUrl;
        this.point = point;
        this.itemDescription = itemDescription;
    }

    public void delete() {
        this.deleted = true;
    }

    public void update(String itemName, String imgUrl, int point, String itemDescription) {
        this.itemName = itemName;
        this.imgUrl = imgUrl;
        this.point = point;
        this.itemDescription = itemDescription;
    }
}
