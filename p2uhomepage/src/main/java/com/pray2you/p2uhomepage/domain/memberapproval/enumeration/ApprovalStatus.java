package com.pray2you.p2uhomepage.domain.memberapproval.enumeration;

import lombok.Getter;

@Getter
public enum ApprovalStatus {

        APPROVED("APPROVED"),
        DELETED("DELETED")
        ;

        String status;

    ApprovalStatus(String status) {
        this.status = status;
    }
}
