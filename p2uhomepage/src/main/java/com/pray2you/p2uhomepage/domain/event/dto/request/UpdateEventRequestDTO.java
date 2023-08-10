package com.pray2you.p2uhomepage.domain.event.dto.request;

import com.pray2you.p2uhomepage.domain.event.entity.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class UpdateEventRequestDTO {

    private Long eventId;
    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime eventStartDate;
    @NotNull
    private LocalDateTime eventEndDate;
    private String contents;

    public Event toEntity(Event event) {
        event.updateEvent(title, eventStartDate, eventEndDate, contents);
        return event;
    }
}
