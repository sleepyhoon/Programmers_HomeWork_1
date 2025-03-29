package io.domain.post.dto;

import java.util.Objects;

public class UpdatePostDto {
    private final Integer id;
    private final Integer currentMemberId;
    private final String title;
    private final String content;

    // private 생성자
    private UpdatePostDto(Integer id, Integer currentMemberId, String title, String content) {
        this.id = id;
        this.currentMemberId = currentMemberId;
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

    public Integer getCurrentMemberId() {
        return currentMemberId;
    }

    // 정적 팩토리 메서드
    public static UpdatePostDto of(Integer id, Integer authorId, String title, String content) {
        return new UpdatePostDto(id, authorId, title, content);
    }
}