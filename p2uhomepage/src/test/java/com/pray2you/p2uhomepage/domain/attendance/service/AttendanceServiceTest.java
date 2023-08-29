package com.pray2you.p2uhomepage.domain.attendance.service;

import com.pray2you.p2uhomepage.domain.attendance.dto.response.CreateAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.dto.response.ReadAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.entity.Attendance;
import com.pray2you.p2uhomepage.domain.attendance.repository.AttendanceRepository;
import com.pray2you.p2uhomepage.domain.model.Role;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    @Test
    @DisplayName("## 출석 정보 등록 서비스 테스트 ##")
    void createAttendance() {

        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Attendance attendance = new Attendance(user);

        CreateAttendanceResponseDTO responseDTO = CreateAttendanceResponseDTO.toDTO(attendance);

        //given
        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
        BDDMockito.given(attendanceRepository.save(any())).willReturn(attendance);

        //when
        CreateAttendanceResponseDTO result = attendanceService.createAttendance(user.getId());

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
    }

    @Test
    @DisplayName("## 출석 정보 조회 서비스 테스트 ##")
    void readAttendance() {

        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Attendance attendance = new Attendance(user);

        ReadAttendanceResponseDTO responseDTO = ReadAttendanceResponseDTO.toDTO(attendance);

        //given
        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
        BDDMockito.given(attendanceRepository.findAllByUserAndCreateDateBetween(any(), any(), any())).willReturn(List.of(attendance));

        //when
        List<ReadAttendanceResponseDTO> result = attendanceService.readAttendance(user.getId(), 2023, 1);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(List.of(responseDTO));
    }
}