package com.domain.dto.board;

public class CreateBoardDto {
    private String title;
    private String content;

    private CreateBoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static CreateBoardDto of(String title, String content) {
        return new CreateBoardDto(title,content);
    }
}
