package com.pray2you.p2uhomepage.domain.event.dto.response;

import com.pray2you.p2uhomepage.domain.event.entity.Event;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class DeleteEventResponseDTO {

    private final long eventId;
    private final long userId;
    private final String title;
    private final LocalDateTime eventStartDate;
    private final LocalDateTime eventEndDate;
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime deletedDate;

    @Builder
    private DeleteEventResponseDTO(
            @NonNull Long eventId,
            @NonNull Long userId,
            @NonNull String title,
            @NonNull LocalDateTime eventStartDate,
            @NonNull LocalDateTime eventEndDate,
            String contents,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime deletedDate) {
        this.eventId = eventId;
        this.userId = userId;
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
                .userId(event.getUser().getId())
                .title(event.getTitle())
                .eventStartDate(event.getEventStartDate())
                .eventEndDate(event.getEventEndDate())
                .contents(event.getContent())
                .createdDate(event.getCreatedDate())
                .deletedDate(event.getModifiedDate())
                .build();
    }
}
