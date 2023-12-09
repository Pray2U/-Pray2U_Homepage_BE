package com.pray2you.p2uhomepage.domain.event.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.global.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime eventStartDate;

    @Column(nullable = false)
    private LocalDateTime eventEndDate;

    @Column
    private String content;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    private Event(
            @NonNull User user,
            @NonNull String title,
            @NonNull LocalDateTime eventStartDate,
            @NonNull LocalDateTime eventEndDate,
            String content) {
        this.user = user;
        this.title = title;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.content = content;
    }

    public void updateEvent(String title, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String content) {
        this.title = title;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.content = content;
    }

    public void updateDelete(boolean delete) {
        deleted = delete;
    }
}
