package com.pray2you.p2uhomepage.domain.order.entity;

import com.pray2you.p2uhomepage.domain.item.entity.Item;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "`order`")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private LocalDateTime useDate;

    @Column(nullable = false)
    private boolean useStatus;

    @Column
    private String targetUser;

    @Builder
    public Order(User user, Item item, LocalDateTime useDate, boolean useStatus, String targetUser) {
        this.user = user;
        this.item = item;
        this.useDate = useDate;
        this.useStatus = useStatus;
        this.targetUser = targetUser;
    }
}
