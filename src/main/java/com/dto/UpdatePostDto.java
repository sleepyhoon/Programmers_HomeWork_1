package com.dto;

import java.util.Objects;

public class UpdatePostDto {
    private final Integer id;
    private final String title;
    private final String content;

    // private 생성자
    private UpdatePostDto(String id, String title, String content) {
        this.id = Integer.valueOf(id.substring(0, id.length() - 1));
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

    // 정적 팩토리 메서드
    public static UpdatePostDto of(String id, String title, String content) {
        return new UpdatePostDto(id, title, content);
    }
}