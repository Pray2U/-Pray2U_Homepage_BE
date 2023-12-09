package com.pray2you.p2uhomepage.domain.til.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.global.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Til extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "til_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column
    private String tag;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    private Til(
            @NonNull User user,
            @NonNull String title,
            String tag,
            @NonNull String content) {
        this.user = user;
        this.title = title;
        this.tag = tag;
        this.content = content;
    }

    public void delete() {
        this.deleted = true;
    }

    public void update(String content, String title, String tag) {
        this.content = content;
        this.title = title;
        this.tag = tag;
    }
}
