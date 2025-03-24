package io.domain.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    // 프롬프트 상수
    private static final String URL_PROMPT = "https://programmers";
    private static final String TITLE_PROMPT = "제목을 입력해주세요 > ";
    private static final String CONTENT_PROMPT = "내용을 입력해주세요 > ";
    private static final String USERNAME_PROMPT = "계정 이름을 입력해주세요 > ";
    private static final String PASSWORD_PROMPT = "비밀번호를 입력해주세요 > ";
    private static final String NICKNAME_PROMPT = "닉네임을 입력해주세요 > ";
    private static final String EMAIL_PROMPT = "이메일을 입력해주세요 > ";

    // 공통 입력 메서드
    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // 사용자 입력 메서드
    public static String getUserCommand() {
        System.out.print(URL_PROMPT);
        return scanner.nextLine().trim();
    }

    public static String getUserTitle() {
        return getInput(TITLE_PROMPT);
    }

    public static String getUserContent() {
        return getInput(CONTENT_PROMPT);
    }

    public static String getUsername() {
        return getInput(USERNAME_PROMPT);
    }

    public static String getUserPassword() {
        return getInput(PASSWORD_PROMPT);
    }

    public static String getUserNickName() {
        return getInput(NICKNAME_PROMPT);
    }

    public static String getUserEmail() {
        return getInput(EMAIL_PROMPT);
    }
}
