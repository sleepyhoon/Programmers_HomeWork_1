package com.domain.dto.board;

import java.util.Objects;

public class UpdateBoardDto {
    private final Integer id;
    private final String title;
    private final String content;

    // private 생성자
    private UpdateBoardDto(String id, String title, String content) {
        this.id = Integer.valueOf(id);
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
    public static UpdateBoardDto of(String id, String title, String content) {
        return new UpdateBoardDto(id, title, content);
    }
}
