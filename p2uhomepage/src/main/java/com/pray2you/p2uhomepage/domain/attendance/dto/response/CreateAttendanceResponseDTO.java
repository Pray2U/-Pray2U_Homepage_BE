package com.pray2you.p2uhomepage.domain.attendance.dto.response;

import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateAttendanceResponseDTO {
    private final long attendanceId;
    private final int point;
    private final LocalDateTime createDate;

    public CreateAttendanceResponseDTO(long attendanceId, int point, LocalDateTime createDate) {
        this.attendanceId = attendanceId;
        this.point = point;
        this.createDate = createDate;
    }

    public static CreateAttendanceResponseDTO toDTO(Attendance attendance, int point) {
        return new CreateAttendanceResponseDTO(attendance.getId(), point, attendance.getCreateDate());
    }
}
