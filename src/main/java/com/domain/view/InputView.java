package com.domain.view;

import java.util.Scanner;

public class InputView {
    private static final String BOARD_URL_START = "https://";
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserCommand() {
        System.out.print(BOARD_URL_START);
        return scanner.nextLine().trim();
    }

    public static String getUserTitle() {
        System.out.print("제목을 입력해주세요 > ");
        return scanner.nextLine().trim();
    }

    public static String getUserContent() {
        System.out.print("내용을 입력해주세요 > ");
        return scanner.nextLine().trim();
    }

    public static String getUsername() {
        System.out.print("계정 이름을 입력해주세요 > ");
        return scanner.nextLine().trim();
    }

    public static String getUserPassword() {
        System.out.print("비밀번호를 입력해주세요 > ");
        return scanner.nextLine().trim();
    }

    public static String getUserNickName() {
        System.out.print("닉네임을 입력해주세요 > ");
        return scanner.nextLine().trim();
    }

    public static String getUserEmail() {
        System.out.print("이메일을 입력해주세요 > ");
        return scanner.nextLine().trim();
    }
}
