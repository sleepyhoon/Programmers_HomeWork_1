package com.domain.entity;

import com.domain.dto.board.CreateBoardDto;
import com.domain.dto.board.UpdateBoardDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private Integer id;
    private String title;

    private String content;

    private List<Post> posts;
    private Board(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.posts = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
    // 처음 만들 때는 레포지토리에서 직접 id를 주입받는다.
    public static Board of(CreateBoardDto dto) {
        return new Board(null, dto.getTitle(), dto.getContent());
    }

    public static Board of(UpdateBoardDto dto) {
        return new Board(dto.getId(),dto.getTitle(),dto.getContent());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", posts=" + posts +
                '}';
    }
}
