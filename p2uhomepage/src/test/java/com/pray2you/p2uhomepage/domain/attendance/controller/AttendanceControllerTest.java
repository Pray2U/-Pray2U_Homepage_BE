package com.pray2you.p2uhomepage.domain.attendance.controller;

import com.pray2you.p2uhomepage.auth.WithMockCustomUser;
import com.pray2you.p2uhomepage.domain.attendance.dto.response.CreateAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.dto.response.ReadAttendanceResponseDTO;
import com.pray2you.p2uhomepage.domain.attendance.service.AttendanceService;
import com.pray2you.p2uhomepage.global.config.WebSecurityConfigure;
import com.pray2you.p2uhomepage.global.security.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@WebMvcTest(controllers = AttendanceController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
        })
class AttendanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;

    @Test
    @WithMockCustomUser
    @DisplayName("## 출석정보 등록 컨트롤러 테스트 ##")
    void createAttendance() throws Exception {

        CreateAttendanceResponseDTO responseDTO = new CreateAttendanceResponseDTO(1L, LocalDateTime.now());

        //given
        BDDMockito.given(attendanceService.createAttendance(anyLong())).willReturn(responseDTO);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/attendance")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockCustomUser
    @DisplayName("## 출석정보 조회 컨트롤러 테스트 ##")
    void readAttendance() throws Exception {
        ReadAttendanceResponseDTO responseDTO = new ReadAttendanceResponseDTO(1L, LocalDateTime.now());

        //given
        BDDMockito.given(attendanceService.readAttendance(anyLong(), anyInt(), anyInt())).willReturn(List.of(responseDTO));

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/attendance/2023/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}