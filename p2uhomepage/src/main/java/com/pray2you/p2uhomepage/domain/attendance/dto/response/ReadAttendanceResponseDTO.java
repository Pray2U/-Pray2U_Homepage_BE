package com.pray2you.p2uhomepage.domain.attendance.dto.response;

import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadAttendanceResponseDTO {
    private final long attendanceId;
    private final LocalDateTime createDate;

    public ReadAttendanceResponseDTO(long attendanceId, LocalDateTime createDate) {
        this.attendanceId = attendanceId;
        this.createDate = createDate;
    }

    public static ReadAttendanceResponseDTO toDTO(Attendance attendance) {
        return new ReadAttendanceResponseDTO(attendance.getId(), attendance.getCreateDate());
    }
}
