package com.global.auth;

public class Session {
    private final Integer currentMemberId;

    public Session(Integer currentMemberId) {
        this.currentMemberId = currentMemberId;
    }

    protected Integer getCurrentMemberId() {
        return this.currentMemberId;
    }
}
