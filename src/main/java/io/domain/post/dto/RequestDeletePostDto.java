package io.domain.post.dto;

import io.global.auth.Session;

public class RequestDeletePostDto {
    private final Session session;

    private RequestDeletePostDto(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public static RequestDeletePostDto from(Session session) {
        return new RequestDeletePostDto(session);
    }
}
