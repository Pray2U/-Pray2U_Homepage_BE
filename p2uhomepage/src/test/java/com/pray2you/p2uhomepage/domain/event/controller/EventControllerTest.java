package com.pray2you.p2uhomepage.domain.event.controller;

import com.pray2you.p2uhomepage.domain.event.dto.response.CreateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.DeleteEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.UpdateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.service.EventService;
import com.pray2you.p2uhomepage.global.config.WebSecurityConfigure;
import com.pray2you.p2uhomepage.global.security.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@WebMvcTest(controllers = EventController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
        })
@AutoConfigureMockMvc(addFilters = false)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;
    @Test
    @DisplayName("## 이벤트 등록 컨트롤러 테스트 ##")
    void createEvent() throws Exception {
        StringBuilder eventCreateRequestJson = new StringBuilder();
        eventCreateRequestJson.append("{")
                .append("\"title\": \"이벤트\",")
                .append("\"eventStartDate\": \"2023-02-01T10:11:22\",")
                .append("\"eventEndDate\": \"2023-02-03T10:11:22\",")
                .append("\"contents\": \"이벤트\"")
                .append("}");

        CreateEventResponseDTO responseDTO = CreateEventResponseDTO.builder()
                .eventId(1L)
                .title("이벤트")
                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
                .contents("이벤트")
                .createdDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(eventService.createEvent(any())).willReturn(responseDTO);

        String json = eventCreateRequestJson.toString();
        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/events")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("## 이벤트 수정 컨트롤러 테스트 ##")
    void updateEvent() throws Exception {
        StringBuilder eventUpdateRequestJson = new StringBuilder();
        eventUpdateRequestJson.append("{")
                .append("\"title\": \"이벤트\",")
                .append("\"eventStartDate\": \"2023-02-01T10:11:22\",")
                .append("\"eventEndDate\": \"2023-02-03T10:11:22\",")
                .append("\"contents\": \"이벤트\"")
                .append("}");

        UpdateEventResponseDTO responseDTO = UpdateEventResponseDTO.builder()
                .eventId(1L)
                .title("이벤트")
                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
                .contents("이벤트")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(eventService.updateEvent(any())).willReturn(responseDTO);

        String json = eventUpdateRequestJson.toString();
        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/events")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("## 멤버 삭제 컨트롤러 테스트 ##")
    void deleteEvent() throws Exception {

        DeleteEventResponseDTO responseDTO = DeleteEventResponseDTO.builder()
                .eventId(1L)
                .title("이벤트")
                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
                .contents("이벤트")
                .createdDate(LocalDateTime.now())
                .deletedDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(eventService.deleteEvent(any())).willReturn(responseDTO);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/events/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("## 해당 달 이벤트 조회 컨트롤러 테스트 ##")
    void readMonthEvent() throws Exception {

        ReadEventResponseDTO responseDTO = ReadEventResponseDTO.builder()
                .eventId(1L)
                .title("이벤트")
                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
                .contents("이벤트")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        List<ReadEventResponseDTO> responseDTOList = List.of(responseDTO);

        //given
        BDDMockito.given(eventService.readMonthEvent(anyInt(),anyInt())).willReturn(responseDTOList);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/events/2023/02")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }


}