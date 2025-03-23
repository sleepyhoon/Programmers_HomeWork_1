package com.domain.dto.post;

import java.util.Objects;

public class CreatePostDto {
    private final Integer boardId;
    private final Integer authorId;
    private final String title;
    private final String content;

    // private 생성자
    private CreatePostDto(String boardId, Integer authorId, String title, String content) {
        this.boardId = Integer.valueOf(boardId);
        this.authorId = authorId;
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // 정적 팩토리 메서드
    public static CreatePostDto of(String boardId, Integer authorId, String title, String content) {
        return new CreatePostDto(boardId, authorId, title, content);
    }
}