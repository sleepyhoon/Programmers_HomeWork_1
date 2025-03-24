package io.domain.post.controller;

import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestDeletePostDto;
import io.domain.post.dto.RequestSelectPostDto;
import io.domain.post.dto.RequestUpdatePostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.post.service.PostService;
import io.domain.view.InputView;
import io.global.auth.Session;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public Integer create(Integer boardId, Session session) {
        Integer authorId = session.getCurrentMemberId();
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        return postService.create(CreatePostDto.of(boardId, authorId, userPostTitle, userPostContent));
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
