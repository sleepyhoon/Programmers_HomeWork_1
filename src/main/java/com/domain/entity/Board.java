package com.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Integer id;
    private String title;
    private String content;
    private List<Post> posts;
    private LocalDateTime created;
    private LocalDateTime updated;

    public void setId(Integer id) {
        this.id = id;
    }

    public Board(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.posts = new ArrayList<>();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
}
