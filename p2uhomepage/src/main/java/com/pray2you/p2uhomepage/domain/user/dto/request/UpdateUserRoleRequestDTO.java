package com.pray2you.p2uhomepage.domain.user.dto.request;

import com.pray2you.p2uhomepage.domain.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class UpdateUserRoleRequestDTO {
    @NotNull
    public Role role;
}
