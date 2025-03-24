package io.domain.board.controller;

import io.domain.board.dto.CreateBoardDto;
import io.domain.board.dto.UpdateBoardDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.board.service.BoardService;
import io.domain.view.InputView;
import io.global.auth.Session;
import io.global.exception.UnauthenticatedException;
import java.util.List;

public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public Integer create(Session session) {
        if (!session.isAdmin()) {
            throw new UnauthenticatedException("관리자 계정이 아닙니다.");
        }
        String userTitle = InputView.getUserTitle();
        String userContent = InputView.getUserContent();
        return boardService.create(CreateBoardDto.of(session.getCurrentMemberId(), userTitle, userContent));
    }

    public List<ResponsePostDto> selectAllPosts(Session session, String boardName) {
        return boardService.selectAllPosts(session, boardName);
    }

    public void delete(Integer boardId) {
        boardService.delete(boardId);
    }

    public void update(Integer boardId) {
        String userBoardTitle = InputView.getUserTitle();
        String userBoardContent = InputView.getUserContent();
        boardService.update(UpdateBoardDto.of(boardId, userBoardTitle, userBoardContent));
    }

}
