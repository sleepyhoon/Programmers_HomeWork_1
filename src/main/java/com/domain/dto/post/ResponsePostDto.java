package com.domain.dto.post;

import com.domain.entity.Post;
import java.time.LocalDateTime;
import java.util.Objects;

public class ResponsePostDto {
    private final String title;
    private final String content;
    private final LocalDateTime created;
    private final LocalDateTime updated;

    // private 생성자
    private ResponsePostDto(String title, String content, LocalDateTime created, LocalDateTime updated) {
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
        this.created = created;
        this.updated = updated;
    }

    public static ResponsePostDto from(Post post) {
        return new ResponsePostDto(post.getTitle(), post.getContent(), post.getCreated(), post.getUpdated());
    }

    @Override
    public String toString() {
        return "ResponsePostDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
