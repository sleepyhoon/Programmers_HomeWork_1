package io.domain.view;

import io.domain.member.dto.ResponseMemberDetail;
import io.domain.post.dto.ResponsePostDto;

import java.util.List;

public class OutputView {
    // 메시지 상수
    private static final String CREATE_SUCCESS = "번 id로 성공적으로 만들었습니다!";
    private static final String UPDATE_SUCCESS = "성공적으로 수정했습니다!";
    private static final String DELETE_SUCCESS = "성공적으로 삭제했습니다!";
    private static final String SIGNUP_SUCCESS = "번 유저를 성공적으로 회원가입 했습니다!";
    private static final String SIGNIN_SUCCESS = "성공적으로 로그인했습니다!";
    private static final String SIGNIN_FAILURE = "아이디나 비밀번호가 틀렸습니다.";
    private static final String SIGNOUT_SUCCESS = "성공적으로 로그아웃했습니다!";
    private static final String MEMBER_UPDATE_SUCCESS = "성공적으로 수정했습니다!";
    private static final String MEMBER_DELETE_SUCCESS = "성공적으로 삭제했습니다!";
    private static final String UPDATE_START = "번 id를 수정합니다.";
    private static final String LOGIN_REQUIRED = "로그인을 먼저 하세요.";
    private static final String INVALID_INPUT = "잘못된 입력입니다.";
    private static final String TERMINATE = "프로그램을 종료합니다.";

    // 공통 출력 메서드
    private static void print(String message) {
        System.out.println(message);
    }

    private static void printWithId(Integer id, String message) {
        System.out.println(id + message);
    }

    // 결과 출력
    public static void showCreateResult(Integer postId) {
        printWithId(postId, CREATE_SUCCESS);
    }

    public static void showUpdateResult(Integer postId) {
        printWithId(postId, UPDATE_SUCCESS);
    }

    public static void showDeleteResult(Integer postId) {
        printWithId(postId, DELETE_SUCCESS);
    }

    public static void showSignUpResult(Integer memberId) {
        printWithId(memberId, SIGNUP_SUCCESS);
    }

    public static void showSignInResult() {
        print(SIGNIN_SUCCESS);
    }

    public static void showSignOutResult() {
        print(SIGNOUT_SUCCESS);
    }

    public static void startUpdate(Integer postId) {
        printWithId(postId, UPDATE_START);
    }

    // 게시물 출력
    public static void showPost(ResponsePostDto post) {
        print(post.toString());
    }

    public static void showAllPosts(List<ResponsePostDto> posts) {
        posts.forEach(post -> print(post.toString()));
    }

    // 회원 출력
    public static void showMemberDetail(ResponseMemberDetail detail) {
        print(detail.toString());
    }

    public static void showMemberUpdateResult() {
        print(MEMBER_UPDATE_SUCCESS);
    }

    public static void showMemberDeleteResult() {
        print(MEMBER_DELETE_SUCCESS);
    }

    // 에러 및 안내 메시지
    public static void showMemberNotFound() {
        print(SIGNIN_FAILURE);
    }

    public static void showLoginRequiredMessage() {
        print(LOGIN_REQUIRED);
    }

    public static void showInvalidCommand() {
        print(INVALID_INPUT);
    }

    public static void showTerminateProgram() {
        print(TERMINATE);
    }
}
