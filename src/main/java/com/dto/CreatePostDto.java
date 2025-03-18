package com.dto;

import com.entity.Post;
import java.util.Objects;

public class CreatePostDto {
    private final String title;
    private final String content;

    // private 생성자
    private CreatePostDto(String title, String content) {
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // 정적 팩토리 메서드
    public static CreatePostDto of(String title, String content) {
        return new CreatePostDto(title, content);
    }
}