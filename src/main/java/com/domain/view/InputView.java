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

    public static String getUserSelectNumber() {
        System.out.print("어떤 게시물을 조회할까요? ");
        return scanner.nextLine().trim();
    }

    public static String getUserUpdateNumber() {
        System.out.print("어떤 게시물을 수정할까요? ");
        return scanner.nextLine().trim();
    }

    public static String getUserDeleteNumber() {
        System.out.print("어떤 게시물을 삭제할까요? ");
        return scanner.nextLine().trim();
    }
}
