package com.pray2you.p2uhomepage.domain.event.dto.response;

import com.pray2you.p2uhomepage.domain.event.entity.Event;
import com.pray2you.p2uhomepage.domain.user.dto.response.SimpleUserInfoResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ReadEventResponseDTO {
    private final long eventId;
    private final String title;
    private final LocalDateTime eventStartDate;
    private final LocalDateTime eventEndDate;
    private final String contents;
    private final SimpleUserInfoResponseDTO user;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadEventResponseDTO(
            @NonNull Long eventId,
            @NonNull SimpleUserInfoResponseDTO user,
            @NonNull String title,
            @NonNull LocalDateTime eventStartDate,
            @NonNull LocalDateTime eventEndDate,
            String contents,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.eventId = eventId;
        this.user = user;
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
                .user(SimpleUserInfoResponseDTO.toDTO(event.getUser()))
                .createdDate(event.getCreatedDate())
                .modifiedDate(event.getModifiedDate())
                .build();
    }
}
