package com.pray2you.p2uhomepage.domain.model;

import lombok.Getter;

@Getter
public enum ApprovalStatus {

        APPROVED("APPROVED"),
        JOINED("JOINED"),
        DELETED("DELETED")
        ;

        String status;

    ApprovalStatus(String status) {
        this.status = status;
    }
}
