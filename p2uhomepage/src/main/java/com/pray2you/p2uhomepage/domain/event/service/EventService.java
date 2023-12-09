package com.pray2you.p2uhomepage.domain.event.service;

import com.pray2you.p2uhomepage.domain.event.dto.request.CreateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.request.UpdateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.CreateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.DeleteEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.UpdateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.entity.Event;
import com.pray2you.p2uhomepage.domain.event.repository.EventRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public CreateEventResponseDTO createEvent(long userId, CreateEventRequestDTO requestDTO) {
        User user = findUser(userId);
        Event savedEvent = eventRepository.save(requestDTO.toEntity(user));
        return CreateEventResponseDTO.toDTO(savedEvent);
    }

    public UpdateEventResponseDTO updateEvent(long userId, UpdateEventRequestDTO requestDTO) {
        User user = findUser(userId);

        Event savedEvent = eventRepository.findEventByIdAndUserAndDeleted(requestDTO.getEventId(), user, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_EVENT_EXCEPTION));

        Event updateEvent = requestDTO.toEntity(savedEvent);

        Event updatedEvent = eventRepository.save(updateEvent);
        return UpdateEventResponseDTO.toDTO(updatedEvent);
    }

    public DeleteEventResponseDTO deleteEvent(long userId, long eventId) {
        User user = findUser(userId);

        Event savedEvent;
        //운영진일 경우, 본인 작성 이벤트 외의 이벤트도 삭제 가능
        if(user.getRole() == Role.ROLE_ADMIN) {
            savedEvent = eventRepository.findEventByIdAndDeleted(eventId, false)
                    .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_EVENT_EXCEPTION));
        }else {
            savedEvent = eventRepository.findEventByIdAndUserAndDeleted(eventId, user, false)
                    .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_EVENT_EXCEPTION));
        }

        savedEvent.updateDelete(true);
        eventRepository.delete(savedEvent);

        return DeleteEventResponseDTO.toDTO(savedEvent);
    }

    public List<ReadEventResponseDTO> readMonthEvent(int year,int month) {

        LocalDateTime date = LocalDateTime.of(year, month, 1, 0, 0);

        List<Event> monthEvent = eventRepository
                .findAllByEventStartDateBetweenOrEventEndDateBetweenAndDeleted(
                        date.with(TemporalAdjusters.firstDayOfMonth()),
                        date.with(TemporalAdjusters.lastDayOfMonth()),
                        date.with(TemporalAdjusters.firstDayOfMonth()),
                        date.with(TemporalAdjusters.lastDayOfMonth()),
                        false);

        return monthEvent.stream()
                .map(ReadEventResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    private User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }

}
