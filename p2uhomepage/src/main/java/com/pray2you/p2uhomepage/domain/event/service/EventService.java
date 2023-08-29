package com.pray2you.p2uhomepage.domain.event.service;

import com.pray2you.p2uhomepage.domain.event.dto.request.CreateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.request.UpdateEventRequestDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.CreateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.DeleteEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.ReadEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.dto.response.UpdateEventResponseDTO;
import com.pray2you.p2uhomepage.domain.event.entity.Event;
import com.pray2you.p2uhomepage.domain.event.repository.EventRepository;
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

    public CreateEventResponseDTO createEvent(CreateEventRequestDTO requestDTO) {
        Event savedEvent = eventRepository.save(requestDTO.toEntity());
        return CreateEventResponseDTO.toDTO(savedEvent);
    }

    public UpdateEventResponseDTO updateEvent(UpdateEventRequestDTO requestDTO) {
        Event savedEvent = eventRepository.findEventByIdAndDeleted(requestDTO.getEventId(), false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_EVENT_EXCEPTION));

        Event updateEvent = requestDTO.toEntity(savedEvent);

        Event updatedEvent = eventRepository.save(updateEvent);
        return UpdateEventResponseDTO.toDTO(updatedEvent);
    }

    public DeleteEventResponseDTO deleteEvent(Long eventId) {

        Event savedEvent = eventRepository.findEventByIdAndDeleted(eventId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_EVENT_EXCEPTION));

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

}
