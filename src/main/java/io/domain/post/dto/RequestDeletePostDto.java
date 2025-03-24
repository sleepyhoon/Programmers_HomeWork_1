package io.domain.post.dto;

import io.global.auth.Session;

public class RequestDeletePostDto {
    private final Session session;
    private final Integer removePostId;
    private RequestDeletePostDto(Session session, Integer removePostId) {
        this.session = session;
        this.removePostId = removePostId;
    }

    public Session getSession() {
        return session;
    }

    public Integer getRemovePostId() {
        return removePostId;
    }

    public static RequestDeletePostDto from(Session session, Integer removePostId) {
        return new RequestDeletePostDto(session,removePostId);
    }
}
