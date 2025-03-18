package com.domain.controller;

import com.domain.dto.CreatePostDto;
import com.domain.dto.ResponsePostDto;
import com.domain.dto.RequestPostDto;
import com.domain.dto.UpdatePostDto;
import com.domain.service.PostService;
import com.domain.view.InputView;

public class PostController {
    private final String CREATE_RESULT = "번 글을 성공적으로 만들었습니다!";
    private final String UPDATE_RESULT = "번 글을 성공적으로 수정했습니다!";
    private final String DELETE_RESULT = "번 글을 성공적으로 삭제했습니다!";

    private InputView inputView;
    private PostService postService;

    public PostController(InputView inputView,PostService postService) {
        this.inputView = inputView;
        this.postService = postService;
    }

    public void play() {
        while(true) {
            String userInput = inputView.getUserCommand();
            switch (userInput) {
                case "작성" -> {
                    Integer createIndex = create();
                    System.out.println(createIndex + CREATE_RESULT);
                }
                case "조회" -> {
                    ResponsePostDto data = select(); // String
                    System.out.println(data);
                }
                case "수정" -> {
                    Integer updateIndex = update();
                    System.out.println(updateIndex + UPDATE_RESULT);
                }
                case "삭제" -> {
                    delete();
                    System.out.println(userInput + DELETE_RESULT);
                }
                default -> System.out.println("잘못된 입력");
            }
        }
    }

    // crud 전부 id는 반환하게 변경해야 함.
    private Integer create() {
        String userPostTitle = inputView.getUserPostTitle();
        String userPostContent = inputView.getUserPostContent();
        return postService.create(CreatePostDto.of(userPostTitle, userPostContent));
    }

    private ResponsePostDto select() {
        String userSelectNumber = inputView.getUserSelectNumber();
        return postService.select(RequestPostDto.from(userSelectNumber));
    }

    private Integer update() {
        String userUpdateNumber = inputView.getUserUpdateNumber();
        System.out.println(userUpdateNumber + "번 게시물을 수정합니다.");
        String userPostTitle = inputView.getUserPostTitle();
        String userPostContent = inputView.getUserPostContent();
        return postService.update(UpdatePostDto.of(userUpdateNumber,userPostTitle,userPostContent));
    }

    private void delete() {
        String userDeleteNumber = inputView.getUserDeleteNumber();
        postService.delete(RequestPostDto.from(userDeleteNumber));
    }
}
