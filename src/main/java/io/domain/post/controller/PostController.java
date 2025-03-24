package io.domain.post.controller;

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
import io.global.auth.Session;

public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    public PostController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    public Integer create(Integer boardId, Session session) {
        Integer authorId = session.getCurrentMemberId();
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        Post newPost = postService.create(CreatePostDto.of(boardId, authorId, userPostTitle, userPostContent));
        boardService.addPostToBoard(newPost);
        return newPost.getId();
    }

    public ResponsePostDto select(Integer userSelectId) {
        return postService.select(RequestSelectPostDto.from(userSelectId));
    }

    public Integer update(Integer userUpdatePostId, Session session) {
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        return postService.update(UpdatePostDto.of(userUpdatePostId, userPostTitle, userPostContent, session));
    }

    public Integer delete(Session session) {
        return postService.delete(RequestDeletePostDto.from(session));
    }
}
