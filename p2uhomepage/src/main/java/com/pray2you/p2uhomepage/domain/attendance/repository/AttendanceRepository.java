package com.pray2you.p2uhomepage.domain.attendance.repository;

import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByUserAndCreateDateGreaterThanEqualAndCreateDateLessThan(User user, LocalDateTime startDate, LocalDateTime endDate);
    boolean existsByUserAndCreateDateGreaterThanEqualAndCreateDateLessThan(User user, LocalDateTime startDate, LocalDateTime endDate);

}
