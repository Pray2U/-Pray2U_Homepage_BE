package com.pray2you.p2uhomepage.domain.post.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.global.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column
    private String fileUrl;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    private Post(
            @NonNull User user,
            @NonNull String title,
            @NonNull String content,
            String fileUrl) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
    }

    public void update(String title, String content, String fileUrl) {
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;

    }

    public void delete() {
        this.deleted = true;
    }
}
