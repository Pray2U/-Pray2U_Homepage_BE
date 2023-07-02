package com.pray2you.p2uhomepage.domain.board.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String fileUrl;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public Board(User user, String title, String content, String fileUrl) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.fileUrl = fileUrl;
    }
}
