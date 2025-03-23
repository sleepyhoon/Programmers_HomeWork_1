package io.domain.post.dto;

import io.global.auth.Session;

public class RequestUpdatePostDto {
    private final Integer id;
    private final Session session;

    private RequestUpdatePostDto(String id, Session session) {
        this.id = Integer.valueOf(id);
        this.session = session;
    }

    public Integer getId() {
        return id;
    }

    public static RequestUpdatePostDto from(String postId, Session session) {
        return new RequestUpdatePostDto(postId, session);
    }
}