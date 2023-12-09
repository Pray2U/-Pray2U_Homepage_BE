package com.pray2you.p2uhomepage.domain.event.service;

import com.google.gson.*;
import com.pray2you.p2uhomepage.domain.event.dto.request.CreateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.request.UpdateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.CreateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.DeleteEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.UpdateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.entity.Event;
import com.pray2you.p2uhomepage.domain.event.repository.EventRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

//    @Mock
//    private EventRepository eventRepository;
//
//    @InjectMocks
//    private EventService eventService;
//
//    @Test
//    @DisplayName("## 이벤트 등록 서비스 테스트 ##")
//    void createEvent() {
//        StringBuilder eventCreateRequestJson = new StringBuilder();
//        eventCreateRequestJson.append("{")
//                .append("\"title\": \"이벤트\",")
//                .append("\"eventStartDate\": \"2023-02-01T10:11:22\",")
//                .append("\"eventEndDate\": \"2023-02-03T10:11:22\",")
//                .append("\"contents\": \"이벤트\"")
//                .append("}");
//
//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer() {
//            @Override
//            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//                    throws JsonParseException {
//                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//            }
//        }).create();
//
//        CreateEventRequestDTO requestDTO
//                = gson.fromJson(eventCreateRequestJson.toString(), CreateEventRequestDTO.class);
//
//        Event event = Event.builder()
//                .title("이벤트")
//                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
//                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
//                .content("이벤트")
//                .build();
//
//        CreateEventResponseDTO responseDTO = CreateEventResponseDTO.toDTO(event);
//
//        //given
//        BDDMockito.given(eventRepository.save(any())).willReturn(event);
//
//        //when
//        CreateEventResponseDTO result = eventService.createEvent(requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 이벤트 수정 서비스 테스트 ##")
//    void updateEvent() {
//
//        StringBuilder eventUpdateRequestJson = new StringBuilder();
//        eventUpdateRequestJson.append("{")
//                .append("\"eventId\": \"1\",")
//                .append("\"title\": \"이벤트\",")
//                .append("\"eventStartDate\": \"2023-02-01T10:11:22\",")
//                .append("\"eventEndDate\": \"2023-02-03T10:11:22\",")
//                .append("\"contents\": \"이벤트\"")
//                .append("}");
//
//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer)
//                (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))).create();
//
//        UpdateEventRequestDTO requestDTO
//                = gson.fromJson(eventUpdateRequestJson.toString(), UpdateEventRequestDTO.class);
//
//        Event event = Event.builder()
//                .title("이벤트")
//                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
//                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
//                .content("이벤트")
//                .build();
//
//        UpdateEventResponseDTO responseDTO = UpdateEventResponseDTO.toDTO(event);
//
//        //given
//        BDDMockito.given(eventRepository.findEventByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(event));
//        BDDMockito.given(eventRepository.save(any())).willReturn(event);
//
//        //when
//        UpdateEventResponseDTO result = eventService.updateEvent(requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("##  이벤트 삭제 서비스 테스트 ##")
//    void deleteEvent() {
//        Event event = Event.builder()
//                .title("이벤트")
//                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
//                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
//                .content("이벤트")
//                .build();
//
//        DeleteEventResponseDTO responseDTO = DeleteEventResponseDTO.toDTO(event);
//
//        //given
//        BDDMockito.given(eventRepository.findEventByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(event));
//
//        //when
//        DeleteEventResponseDTO result = eventService.deleteEvent(event.getId());
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("##  이벤트 조회 서비스 테스트 ##")
//    void readMonthEvent() {
//
//        Event event = Event.builder()
//                .title("이벤트")
//                .eventStartDate(LocalDateTime.parse("2023-02-01T10:11:22"))
//                .eventEndDate(LocalDateTime.parse("2023-02-03T10:11:22"))
//                .content("이벤트")
//                .build();
//
//        List<Event> eventList = List.of(event);
//
//        List<ReadEventResponseDTO> responseDTOList = eventList.stream()
//                .map(ReadEventResponseDTO::toDTO)
//                .collect(Collectors.toList());
//
//        //given
//        BDDMockito.given(eventRepository.findAllByEventStartDateBetweenOrEventEndDateBetweenAndDeleted(any(), any(), any(), any(), anyBoolean())).willReturn(eventList);
//
//        //when
//        List<ReadEventResponseDTO> result = eventService.readMonthEvent(2023, 2);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOList);
//    }
}