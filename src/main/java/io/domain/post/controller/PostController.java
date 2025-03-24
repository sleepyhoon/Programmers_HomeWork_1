package io.domain.post.controller;

import io.domain.Controller;
import io.domain.board.service.BoardService;
import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestDeletePostDto;
import io.domain.post.dto.RequestSelectPostDto;
import io.domain.post.dto.RequestUpdatePostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.post.entity.Post;
import io.domain.post.service.PostService;
import io.domain.view.InputView;
import io.domain.view.OutputView;
import io.global.auth.Session;
import io.global.util.UserRequest;

public class PostController implements Controller {
    private final PostService postService;
    private final BoardService boardService;

    public PostController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    @Override
    public void requestHandle(UserRequest userRequest) {

        switch (userRequest.getTarget()) {
            case "add" -> {
                if (!userRequest.hasParam("boardId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                Integer boardId = userRequest.getValue("boardId", Integer.class);
                Session session = userRequest.getSession();
                if (session == null || session.getCurrentMemberId() == null) {
                    OutputView.showLoginRequiredMessage();
                    return;
                }
                OutputView.showCreateResult(create(boardId, session));
            }
            case "edit" -> {
                if (!userRequest.hasParam("postId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                Integer postId = userRequest.getValue("postId", Integer.class);
                Session session = userRequest.getSession();
                if (session == null || session.getCurrentMemberId() == null) {
                    OutputView.showLoginRequiredMessage();
                    return;
                }
                OutputView.startUpdate(postId);
                OutputView.showUpdateResult(update(postId, session));
            }
            case "remove" -> {
                if (!userRequest.hasParam("postId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer postId = userRequest.getValue("postId", Integer.class);

                Session session = userRequest.getSession();
                if (session == null || session.getCurrentMemberId() == null) {
                    OutputView.showLoginRequiredMessage();
                    return;
                }

                OutputView.showDeleteResult(delete(session, postId));
            }
            case "view" -> {
                if (!userRequest.hasParam("postId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer postId = userRequest.getValue("postId", Integer.class);
                OutputView.showPost(select(postId));
            }
            default -> OutputView.showInvalidCommand();
        }
    }

    private Integer create(Integer boardId, Session session) {
        Integer authorId = session.getCurrentMemberId();
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        Post newPost = postService.create(CreatePostDto.of(boardId, authorId, userPostTitle, userPostContent));
        boardService.addPostToBoard(newPost);
        return newPost.getId();
    }

    private ResponsePostDto select(Integer userSelectId) {
        return postService.select(RequestSelectPostDto.from(userSelectId));
    }

    private Integer update(Integer userUpdatePostId, Session session) {
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        return postService.update(UpdatePostDto.of(userUpdatePostId, userPostTitle, userPostContent, session));
    }

    private Integer delete(Session session, Integer removePostId) {
        return postService.delete(RequestDeletePostDto.from(session, removePostId));
    }

}
