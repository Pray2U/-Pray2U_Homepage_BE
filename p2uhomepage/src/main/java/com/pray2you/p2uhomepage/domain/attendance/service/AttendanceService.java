package com.pray2you.p2uhomepage.domain.attendance.service;

import com.pray2you.p2uhomepage.domain.attendance.dto.response.CreateAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.dto.response.ReadAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import com.pray2you.p2uhomepage.domain.attendance.repository.AttendanceRepository;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.point.event.PointAddEvent;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.utils.RandomPointUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateAttendanceResponseDTO createAttendance(long userId) {

        User user = findUser(userId);

        Attendance attendance = new Attendance(user);

        //중복 출석 방지, 출석시 예외 처리
        checkTodayAttendance(user);

        int randomPoint = RandomPointUtil.getRandomPoint(PointContent.ATTENDANCE);

        Attendance savedAttendance = attendanceRepository.save(attendance);
        
        //포인트 증가 이벤트 생성
        applicationEventPublisher.publishEvent(new PointAddEvent(PointContent.ATTENDANCE, randomPoint, user));

        return CreateAttendanceResponseDTO.toDTO(savedAttendance, randomPoint);
    }

    public List<ReadAttendanceResponseDTO> readAttendance(long userId,int year, int month) {

        User user = findUser(userId);

        LocalDateTime date = LocalDateTime.of(year, month, 1, 0, 0);

        List<Attendance> attendanceList = attendanceRepository.findAllByUserAndCreateDateGreaterThanEqualAndCreateDateLessThan(
                user,
                date.with(TemporalAdjusters.firstDayOfMonth()),
                date.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1)
            );

        return attendanceList.stream()
                .map(ReadAttendanceResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    private User findUser(long userId){
       return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }

    private void checkTodayAttendance(User user) {
        LocalDateTime today = LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(0, 0, 0));

        if(attendanceRepository.existsByUserAndCreateDateGreaterThanEqualAndCreateDateLessThan(user, today, today.plusDays(1))) {
            throw new RestApiException(UserErrorCode.DUPLICATE_ATTENDANCE_EXCEPTION);
        }

    }
}
