package com.domain.dto.post;

import com.domain.entity.Post;
import java.util.Objects;

public class ResponsePostDto {
    private final String title;
    private final String content;

    // private 생성자
    private ResponsePostDto(String title, String content) {
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static ResponsePostDto from(Post post) {
        return new ResponsePostDto(post.getTitle(), post.getContent());
    }

    // equals & hashCode 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponsePostDto)) {
            return false;
        }
        ResponsePostDto responsePostDto = (ResponsePostDto) o;
        return title.equals(responsePostDto.title) && content.equals(responsePostDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }

    // toString 오버라이드
    @Override
    public String toString() {
        return "PostDto[" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ']';
    }
}
