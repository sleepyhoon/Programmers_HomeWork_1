package com.domain.service;

import com.domain.repository.BoardRepository;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Integer selectAllPosts() {
        return 0;
    }

    public Integer create() {
        return 0;
    }

    public Integer delete() {
        return 0;
    }

    public Integer update() {
        return 0;
    }

}
