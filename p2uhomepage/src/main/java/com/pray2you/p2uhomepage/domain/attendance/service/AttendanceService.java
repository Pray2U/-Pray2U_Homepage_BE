package com.pray2you.p2uhomepage.domain.attendance.service;

import com.pray2you.p2uhomepage.domain.attendance.dto.response.CreateAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.dto.response.ReadAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import com.pray2you.p2uhomepage.domain.attendance.repository.AttendanceRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public CreateAttendanceResponseDTO createAttendance(long userId) {

        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
        Attendance attendance = new Attendance(user);


        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime today = LocalDateTime.of(date, time);

        List<Attendance> attendances =  attendanceRepository.findAllByUserAndCreateDateBetween(user, today, today.plusDays(1));

        if(!attendances.isEmpty()) {
            throw new RestApiException(UserErrorCode.DUPLICATE_ATTENDANCE_EXCEPTION);
        }

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return CreateAttendanceResponseDTO.toDTO(savedAttendance);
    }

    public List<ReadAttendanceResponseDTO> readAttendance(long userId,int year, int month) {

        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        LocalDateTime date = LocalDateTime.of(year, month, 1, 0, 0);

        List<Attendance> attendanceList = attendanceRepository.findAllByUserAndCreateDateBetween(
                user,
                date.with(TemporalAdjusters.firstDayOfMonth()),
            date.with(TemporalAdjusters.lastDayOfMonth())
            );

        return attendanceList.stream()
                .map(ReadAttendanceResponseDTO::toDTO)
                .collect(Collectors.toList());
    }
}
