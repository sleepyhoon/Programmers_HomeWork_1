package io.domain.post.dto;

import io.global.auth.Session;

public class RequestDeletePostDto {
    private final Integer id;
    private final Session session;

    private RequestDeletePostDto(String id, Session session) {
        this.id = Integer.valueOf(id);
        this.session = session;
    }

    public Integer getId() {
        return id;
    }

    public static RequestDeletePostDto from(String postId, Session session) {
        return new RequestDeletePostDto(postId, session);
    }
}
