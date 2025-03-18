package com.domain.controller;

import com.domain.dto.CreatePostDto;
import com.domain.dto.ResponsePostDto;
import com.domain.dto.RequestPostDto;
import com.domain.dto.UpdatePostDto;
import com.domain.service.PostService;
import com.domain.view.InputView;
import com.domain.view.OutputView;
import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void play() {
        try {
            while(true) {
                String userInput = InputView.getUserCommand();
                switch (userInput) {
                    case "작성" -> OutputView.showCreateResult(create());
                    case "조회" -> OutputView.showPost(select());
                    case "수정" -> OutputView.showUpdateResult(update());
                    case "삭제" -> OutputView.showDeleteResult(delete());
                    case "목록" -> OutputView.showAllPosts(selectAll());
                    default -> OutputView.showInvalidCommand();
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // crud 전부 id는 반환하게 변경해야 함.
    private Integer create() {
        String userPostTitle = InputView.getUserPostTitle();
        String userPostContent = InputView.getUserPostContent();
        return postService.create(CreatePostDto.of(userPostTitle, userPostContent));
    }

    private ResponsePostDto select() {
        String userSelectNumber = InputView.getUserSelectNumber();
        return postService.select(RequestPostDto.from(userSelectNumber));
    }

    private Integer update() {
        String userUpdateNumber = InputView.getUserUpdateNumber();
        System.out.println(userUpdateNumber + "번 게시물을 수정합니다.");
        String userPostTitle = InputView.getUserPostTitle();
        String userPostContent = InputView.getUserPostContent();
        return postService.update(UpdatePostDto.of(userUpdateNumber,userPostTitle,userPostContent));

    }

    private Integer delete() {
        String userDeleteNumber = InputView.getUserDeleteNumber();
        postService.delete(RequestPostDto.from(userDeleteNumber));
        return Integer.valueOf(userDeleteNumber);
    }

    private List<ResponsePostDto> selectAll() {
        return postService.selectAll();
    }
}
