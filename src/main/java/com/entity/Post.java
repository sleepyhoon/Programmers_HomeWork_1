package com.entity;

import com.dto.CreatePostDto;
import com.dto.UpdatePostDto;
import java.util.Objects;

public class Post {
    private Integer id;
    private String title;
    private String content;

    private Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private Post(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Post from(CreatePostDto createPostDto) {
        Objects.requireNonNull(createPostDto.getTitle());
        Objects.requireNonNull(createPostDto.getContent());
        return new Post(createPostDto.getTitle(), createPostDto.getContent());
    }

    public static Post from(UpdatePostDto updatePostDto) {
        Objects.requireNonNull(updatePostDto.getId());
        Objects.requireNonNull(updatePostDto.getTitle());
        Objects.requireNonNull(updatePostDto.getContent());
        return new Post(updatePostDto.getId(), updatePostDto.getTitle(), updatePostDto.getContent());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
