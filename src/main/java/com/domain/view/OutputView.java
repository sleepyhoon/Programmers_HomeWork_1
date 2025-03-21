package com.domain.view;

import com.domain.dto.member.ResponseMemberDetail;
import com.domain.dto.post.ResponsePostDto;
import java.util.List;

public class OutputView {
    private static final String CREATE_RESULT_SUCCESS = "번 글을 성공적으로 만들었습니다!";
    private static final String UPDATE_RESULT_SUCCESS = "번 글을 성공적으로 수정했습니다!";
    private static final String UPDATE_START = "번 글을 수정합니다.";
    private static final String SIGNUP_RESULT_SUCCESS = "번 유저를 성공적으로 회원가입 했습니다!";
    private static final String DELETE_RESULT_SUCCESS = "번 글을 성공적으로 삭제했습니다!";
    private static final String SIGNIN_RESULT_SUCCESS = "성공적으로 로그인했습니다!";
    private static final String SIGNOUT_RESULT_SUCCESS = "성공적으로 로그아웃했습니다!";
    private static final String NOT_FOUND_ERROR = "해당 멤버를 찾을 수 없습니다.";
    private static final String INVALID_INPUT = "잘못된 입력입니다.";

    public static void showCreateResult(Integer postId) {
        System.out.println(postId + CREATE_RESULT_SUCCESS);
    }

    public static void showUpdateResult(Integer postId) {
        System.out.println(postId + UPDATE_RESULT_SUCCESS);
    }

    public static void showDeleteResult(Integer postId) {
        System.out.println(postId + DELETE_RESULT_SUCCESS);
    }

    public static void showSignUpResult(Integer memberId) {
        System.out.println(memberId + SIGNUP_RESULT_SUCCESS);
    }

    public static void showSignInResult() {
        System.out.print(SIGNIN_RESULT_SUCCESS);
    }

    public static void showSignOutResult() {
        System.out.print(SIGNOUT_RESULT_SUCCESS);
    }

    public static void startUpdate(String postId) {
        System.out.println(postId + UPDATE_START);
    }

    // 다형성 도입해서 조상 Response 객체를 만들면 메서드를 1개로 압축할 수 있을 것 같다.
    public static void showPost(ResponsePostDto post) {
        System.out.println(post);
    }

    public static void showAllPosts(List<ResponsePostDto> posts) {
        for (ResponsePostDto post : posts) {
            System.out.println(post);
        }
    }

    public static void showMemberDetail(ResponseMemberDetail detail) {
        System.out.println(detail);
    }

    public static void showMemberNotFound() {
        System.out.println(NOT_FOUND_ERROR);
    }

    public static void showInvalidCommand() {
        System.out.println(INVALID_INPUT);
    }
}
