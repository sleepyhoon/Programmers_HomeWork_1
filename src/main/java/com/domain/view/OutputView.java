package com.domain.view;

import com.domain.dto.ResponsePostDto;
import java.util.List;

public class OutputView {
    private static final String CREATE_RESULT = "번 글을 성공적으로 만들었습니다!";
    private static final String UPDATE_RESULT = "번 글을 성공적으로 수정했습니다!";
    private static final String DELETE_RESULT = "번 글을 성공적으로 삭제했습니다!";
    private static final String NOT_FOUND_ERROR = "해당 게시글을 찾을 수 없습니다.";
    private static final String INVALID_INPUT = "잘못된 입력입니다.";

    public static void showCreateResult(Integer postId) {
        System.out.println(postId + CREATE_RESULT);
    }

    public static void showUpdateResult(Integer postId) {
        System.out.println(postId + UPDATE_RESULT);
    }

    public static void showDeleteResult(Integer postId) {
        System.out.println(postId + DELETE_RESULT);
    }

    public static void showPost(ResponsePostDto post) {
        System.out.println(post);
    }

    public static void showAllPosts(List<ResponsePostDto> posts) {
        System.out.println("총 게시글은 " + posts.size() + "개 작성되어있습니다.");
        for (ResponsePostDto post : posts) {
            System.out.println(post);
        }
    }

    public static void showInvalidCommand() {
        System.out.println(INVALID_INPUT);
    }
}
