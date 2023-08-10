package com.pray2you.p2uhomepage.domain.event.dto.response;

import com.pray2you.p2uhomepage.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadEventResponseDTO {
    private Long eventId;
    private String title;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReadEventResponseDTO(Long eventId, String title, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String contents, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.eventId = eventId;
        this.title = title;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.contents = contents;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadEventResponseDTO toDTO(Event event) {
        return builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .eventStartDate(event.getEventStartDate())
                .eventEndDate(event.getEventEndDate())
                .contents(event.getContent())
                .createdDate(event.getCreatedDate())
                .modifiedDate(event.getModifiedDate())
                .build();
    }
}
