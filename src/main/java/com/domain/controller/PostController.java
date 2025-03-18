package com.domain.controller;

import com.domain.dto.CreatePostDto;
import com.domain.dto.RequestPostDto;
import com.domain.dto.ResponsePostDto;
import com.domain.dto.UpdatePostDto;
import com.domain.service.PostService;
import com.domain.view.InputView;
import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // crud 전부 id는 반환하게 변경해야 함.
    public Integer create() {
        String userPostTitle = InputView.getUserPostTitle();
        String userPostContent = InputView.getUserPostContent();
        return postService.create(CreatePostDto.of(userPostTitle, userPostContent));
    }

    public ResponsePostDto select() {
        String userSelectNumber = InputView.getUserSelectNumber();
        return postService.select(RequestPostDto.from(userSelectNumber));
    }

    public Integer update() {
        String userUpdateNumber = InputView.getUserUpdateNumber();
        System.out.println(userUpdateNumber + "번 게시물을 수정합니다.");
        String userPostTitle = InputView.getUserPostTitle();
        String userPostContent = InputView.getUserPostContent();
        return postService.update(UpdatePostDto.of(userUpdateNumber,userPostTitle,userPostContent));

    }

    public Integer delete() {
        String userDeleteNumber = InputView.getUserDeleteNumber();
        postService.delete(RequestPostDto.from(userDeleteNumber));
        return Integer.valueOf(userDeleteNumber);
    }

    public List<ResponsePostDto> selectAll() {
        return postService.selectAll();
    }
}
