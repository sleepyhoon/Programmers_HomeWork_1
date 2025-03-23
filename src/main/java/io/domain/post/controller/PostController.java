package io.domain.post.controller;

import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestPostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.post.service.PostService;
import io.domain.view.InputView;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public Integer create(String boardId, Integer authorId) {
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        return postService.create(CreatePostDto.of(boardId, authorId, userPostTitle, userPostContent));
    }

    public ResponsePostDto select(String userSelectId) {
        return postService.select(RequestPostDto.from(userSelectId));
    }

    public Integer update(String userUpdatePostId) {
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        return postService.update(UpdatePostDto.of(userUpdatePostId, userPostTitle, userPostContent));
    }

    public Integer delete(String userDeleteId) {
        postService.delete(RequestPostDto.from(userDeleteId));
        return Integer.valueOf(userDeleteId);
    }

//    public List<ResponsePostDto> selectAll() {
//        return postService.selectAll();
//    }
}
