package com.domain.dto.post;

import com.domain.entity.Post;
import java.time.LocalDateTime;
import java.util.Objects;

public class ResponsePostDto {
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDateTime created;
    private final LocalDateTime updated;

    // private 생성자
    private ResponsePostDto(String title, String content, String authorName, LocalDateTime created, LocalDateTime updated) {
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
        this.authorName = authorName;
        this.created = created;
        this.updated = updated;
    }

    public static ResponsePostDto of(Post post,String username) {
        if(username == null) {
            return new ResponsePostDto(post.getTitle(), post.getContent(), "비회원", post.getCreated(), post.getUpdated());
        }
        return new ResponsePostDto(post.getTitle(), post.getContent(), username, post.getCreated(), post.getUpdated());
    }

    @Override
    public String toString() {
        return "ResponsePostDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", username='" + authorName + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
