package com.pray2you.p2uhomepage.domain.attendance.entity;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    private LocalDateTime createDate;

    public Attendance(User user) {
        this.user = user;
    }
}
