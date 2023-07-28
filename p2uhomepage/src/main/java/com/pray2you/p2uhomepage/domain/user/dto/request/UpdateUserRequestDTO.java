package com.pray2you.p2uhomepage.domain.user.dto.request;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateUserRequestDTO {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String profileImgUrl;

    public User toEntity(User user){
        return user.update(phoneNumber, profileImgUrl);
    }
}
