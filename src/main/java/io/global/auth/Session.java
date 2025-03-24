package io.global.auth;

import io.domain.member.role.Role;

public class Session {
    private final Integer currentMemberId;
    private final Role role;

    public Session(Integer currentMemberId, Role role) {
        this.currentMemberId = currentMemberId;
        this.role = role;
    }

    public Integer getCurrentMemberId() {
        return this.currentMemberId;
    }

    public boolean isNotAdmin() {
        return !this.role.equals(Role.ADMIN);
    }
}
