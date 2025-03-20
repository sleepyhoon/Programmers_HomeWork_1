package com.domain.controller;

import com.domain.dto.board.CreateBoardDto;
import com.domain.dto.board.UpdateBoardDto;
import com.domain.dto.post.ResponsePostDto;
import com.domain.service.BoardService;
import com.domain.view.InputView;
import java.util.List;

public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public Integer create() {
        String userTitle = InputView.getUserTitle();
        String userContent = InputView.getUserContent();
        return boardService.create(CreateBoardDto.of(userTitle,userContent));
    }

    public List<ResponsePostDto> selectAllPosts(String boardName) {
        return boardService.selectAllPosts(boardName);
    }

    public Integer delete(String boardId) {
        Integer id = Integer.valueOf(boardId);
        boardService.delete(id);
        return id;
    }

    public Integer update(String boardId) {
        String userBoardTitle = InputView.getUserTitle();
        String userBoardContent = InputView.getUserContent();
        boardService.update(UpdateBoardDto.of(boardId,userBoardTitle,userBoardContent));
        return Integer.valueOf(boardId);
    }

}
