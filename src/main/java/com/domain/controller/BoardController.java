package com.domain.controller;

import com.domain.service.BoardService;

public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public Integer selectAllPosts(String boardName) {
        return 0;
    }

    public Integer create() {
        return 0;
    }

    public Integer delete(String boardId) {
        return 0;
    }

    public Integer update(String boardId) {
        return 0;
    }

}
