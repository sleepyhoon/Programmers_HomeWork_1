package com.domain.entity;

import com.domain.dto.post.CreatePostDto;
import com.domain.dto.post.UpdatePostDto;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post implements Comparable<Post> {
    private Integer id;
    private Integer boardId;
    private Integer memberId;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;


    private Post(Integer id, Integer boardId, Integer memberId, String title, String content) {
        this.id = id;
        this.boardId = boardId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private Post(Integer id, Integer boardId, String title, String content, LocalDateTime created) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.created = created;
        this.updated = LocalDateTime.now();
    }

    // id는 레포지토리에서 배정할 것이기 때문에 null로 선언한다.
    public static Post of(CreatePostDto postDto, Integer currentMemberId) {
        return new Post(null, postDto.getBoardId(), currentMemberId, postDto.getTitle(), postDto.getContent());
    }

    public static Post of(UpdatePostDto updatePostDto, Integer boardId, LocalDateTime created) {
        return new Post(updatePostDto.getId(), boardId, updatePostDto.getTitle(), updatePostDto.getContent(), created);
    }

    public Integer getBoardId() {
        return boardId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
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
                ", boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public int compareTo(Post o) {
        return this.id - o.id;
    }
}
