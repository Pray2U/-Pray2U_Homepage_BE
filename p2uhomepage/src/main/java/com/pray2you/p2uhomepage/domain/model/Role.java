package com.pray2you.p2uhomepage.domain.model;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_GUEST("ROLE_GUEST"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }
}
