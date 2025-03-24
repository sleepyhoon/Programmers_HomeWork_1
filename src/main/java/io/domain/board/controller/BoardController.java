package io.domain.board.controller;

import io.domain.Controller;
import io.domain.board.dto.CreateBoardDto;
import io.domain.board.dto.UpdateBoardDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.board.service.BoardService;
import io.domain.view.InputView;
import io.domain.view.OutputView;
import io.global.auth.Session;
import io.global.exception.UnauthenticatedException;
import io.global.util.UserRequest;
import java.util.List;

public class BoardController implements Controller {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public void requestHandle(UserRequest userRequest) {
        switch (userRequest.getTarget()) {
            case "view" -> {
                if (!userRequest.hasParam("boardName")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                String boardName = userRequest.getValue("boardName", String.class);
                OutputView.showAllPosts(selectAllPosts(userRequest.getSession(), boardName));
            }
            case "add" -> {
                OutputView.showCreateResult(create(userRequest.getSession()));
            }
            case "edit" -> {
                if (!userRequest.hasParam("boardId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer boardId = userRequest.getValue("boardId", Integer.class);
                update(boardId);
                OutputView.showUpdateResult(boardId);
            }
            case "remove" -> {
                if (!userRequest.hasParam("boardId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer boardId = userRequest.getValue("boardId", Integer.class);
                delete(boardId);
                OutputView.showDeleteResult(boardId);
            }
            default -> OutputView.showInvalidCommand();
        }
    }

    private Integer create(Session session) {
        if (session.isNotAdmin()) {
            throw new UnauthenticatedException("관리자 계정이 아닙니다.");
        }
        String userTitle = InputView.getUserTitle();
        String userContent = InputView.getUserContent();
        return boardService.create(CreateBoardDto.of(session.getCurrentMemberId(), userTitle, userContent));
    }

    private List<ResponsePostDto> selectAllPosts(Session session, String boardName) {
        return boardService.selectAllPosts(session, boardName);
    }

    private void delete(Integer boardId) {
        boardService.delete(boardId);
    }

    private void update(Integer boardId) {
        String userBoardTitle = InputView.getUserTitle();
        String userBoardContent = InputView.getUserContent();
        boardService.update(UpdateBoardDto.of(boardId, userBoardTitle, userBoardContent));
    }
}
