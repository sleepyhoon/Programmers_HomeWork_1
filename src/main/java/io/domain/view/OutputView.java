package io.domain.view;

import io.domain.member.dto.ResponseMemberDetail;
import io.domain.post.dto.ResponsePostDto;
import java.util.List;

public class OutputView {
    private static final String CREATE_RESULT_SUCCESS = "번 글을 성공적으로 만들었습니다!";
    private static final String UPDATE_RESULT_SUCCESS = "번 글을 성공적으로 수정했습니다!";
    private static final String UPDATE_START = "번 글을 수정합니다.";
    private static final String SIGNUP_RESULT_SUCCESS = "번 유저를 성공적으로 회원가입 했습니다!";
    private static final String DELETE_RESULT_SUCCESS = "번 글을 성공적으로 삭제했습니다!";
    private static final String SIGNIN_RESULT_SUCCESS = "성공적으로 로그인했습니다!";
    private static final String SIGNIN_RESULT_FAILURE = "아이디나 비밀번호가 틀렸습니다.";
    private static final String SIGNOUT_RESULT_SUCCESS = "성공적으로 로그아웃했습니다!";
    private static final String MEMBER_UPDATE_SUCCESS = "성공적으로 수정했습니다!";
    private static final String MEMBER_DELETE_SUCCESS = "성공적으로 삭제했습니다!";

    private static final String LOGIN_REQUIRED_MESSAGE = "로그인을 먼저 하세요.";
    private static final String INVALID_INPUT = "잘못된 입력입니다.";
    private static final String TERMINATE_PROGRAM = "프로그램을 종료합니다.";

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
        System.out.println(SIGNIN_RESULT_SUCCESS);
    }

    public static void showSignOutResult() {
        System.out.println(SIGNOUT_RESULT_SUCCESS);
    }

    public static void startUpdate(Integer postId) {
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

    public static void showMemberUpdateResult() {
        System.out.println(MEMBER_UPDATE_SUCCESS);
    }

    public static void showMemberDeleteResult() {
        System.out.println(MEMBER_DELETE_SUCCESS);
    }

    public static void showMemberNotFound() {
        System.out.println(SIGNIN_RESULT_FAILURE);
    }

    public static void showLoginRequiredMessage() {
        System.out.println(LOGIN_REQUIRED_MESSAGE);
    }

    public static void showInvalidCommand() {
        System.out.println(INVALID_INPUT);
    }

    public static void showTerminateProgram() {
        System.out.println(TERMINATE_PROGRAM);
    }
}
