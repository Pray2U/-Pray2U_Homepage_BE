package com.pray2you.p2uhomepage.domain.event.dto.request;

import com.pray2you.p2uhomepage.domain.event.entity.Event;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateEventRequestDTO {

    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime eventStartDate;
    @NotNull
    private LocalDateTime eventEndDate;
    private String contents;

    public Event toEntity(User user) {
        return Event.builder()
                .user(user)
                .title(this.title)
                .eventStartDate(this.eventStartDate)
                .eventEndDate(this.eventEndDate)
                .content(this.contents)
                .build();
    }
}
