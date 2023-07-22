package com.pray2you.p2uhomepage.domain.event.entity;

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
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

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
    public Event(String title, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String content) {
        this.title = title;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.content = content;
    }
}
