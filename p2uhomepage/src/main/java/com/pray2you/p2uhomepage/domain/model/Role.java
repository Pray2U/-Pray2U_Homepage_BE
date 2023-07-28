package com.pray2you.p2uhomepage.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonCreator
    public static Role from(String value) {
        for (Role role : Role.values()) {
            if (role.getRole().equals(value)) {
                return role;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return role;
    }
}
