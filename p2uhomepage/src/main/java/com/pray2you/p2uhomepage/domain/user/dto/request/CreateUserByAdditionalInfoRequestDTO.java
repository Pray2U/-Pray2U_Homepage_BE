package com.pray2you.p2uhomepage.domain.user.dto.request;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserByAdditionalInfoRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Email
    private String email;
    @Nullable
    private String profileImgUrl;

    public User toEntity(User user) {
        user.addInformation(username, phoneNumber, email, profileImgUrl);
        return user;
    }
}
