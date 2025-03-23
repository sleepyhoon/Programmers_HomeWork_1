package io.domain.board.controller;

import io.domain.board.dto.CreateBoardDto;
import io.domain.board.dto.UpdateBoardDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.board.service.BoardService;
import io.domain.view.InputView;
import java.util.List;

public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public Integer create(Integer authorId) {
        String userTitle = InputView.getUserTitle();
        String userContent = InputView.getUserContent();
        return boardService.create(CreateBoardDto.of(authorId, userTitle, userContent));
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
        boardService.update(UpdateBoardDto.of(boardId, userBoardTitle, userBoardContent));
        return Integer.valueOf(boardId);
    }

}
