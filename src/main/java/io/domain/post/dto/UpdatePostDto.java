package io.domain.post.dto;

import io.global.auth.Session;
import java.util.Objects;

public class UpdatePostDto {
    private final Integer id;
    private final Session session;
    private final String title;
    private final String content;

    // private 생성자
    private UpdatePostDto(String id, Session session, String title, String content) {
        this.id = Integer.valueOf(id);
        this.session = session;
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Session getSession() {
        return session;
    }

    // 정적 팩토리 메서드
    public static UpdatePostDto of(String id, String title, String content, Session session) {
        return new UpdatePostDto(id, session, title, content);
    }
}