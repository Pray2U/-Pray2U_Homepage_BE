package com.pray2you.p2uhomepage.domain.event.dto.response;

import com.pray2you.p2uhomepage.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeleteEventResponseDTO {

    private Long eventId;
    private String title;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;

    @Builder
    public DeleteEventResponseDTO(Long eventId, String title, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String contents, LocalDateTime createdDate, LocalDateTime deletedDate) {
        this.eventId = eventId;
        this.title = title;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.contents = contents;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
    }

    public static DeleteEventResponseDTO toDTO(Event event) {
        return builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .eventStartDate(event.getEventStartDate())
                .eventEndDate(event.getEventEndDate())
                .contents(event.getContent())
                .createdDate(event.getCreatedDate())
                .deletedDate(event.getModifiedDate())
                .build();
    }
}
