package com.pray2you.p2uhomepage.domain.attendance.dto.response;

import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateAttendanceResponseDTO {
    private Long attendanceId;
    private LocalDateTime createDate;

    public CreateAttendanceResponseDTO(Long attendanceId, LocalDateTime createDate) {
        this.attendanceId = attendanceId;
        this.createDate = createDate;
    }

    public static CreateAttendanceResponseDTO toDTO(Attendance attendance) {
        return new CreateAttendanceResponseDTO(attendance.getId(), attendance.getCreateDate());
    }
}
