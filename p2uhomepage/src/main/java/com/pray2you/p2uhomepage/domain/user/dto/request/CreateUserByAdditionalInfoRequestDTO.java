package com.pray2you.p2uhomepage.domain.user.dto.request;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String profileImgUrl;

    public User toEntity(User user) {
        return user.addInformation(username, phoneNumber, email, profileImgUrl);
    }
}
