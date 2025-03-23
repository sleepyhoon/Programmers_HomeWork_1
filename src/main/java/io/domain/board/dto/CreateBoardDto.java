package io.domain.board.dto;

public class CreateBoardDto {
    private Integer authorId;
    private String title;
    private String content;

    private CreateBoardDto(Integer authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static CreateBoardDto of(Integer authorId, String title, String content) {
        return new CreateBoardDto(authorId, title, content);
    }
}
