package com.domain.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserCommand() {
        System.out.print("명령어 > ");
        return scanner.nextLine();
    }

    public static String getUserPostTitle() {
        System.out.print("글의 제목을 입력해주세요 > ");
        return scanner.nextLine();
    }

    public static String getUserPostContent() {
        System.out.print("글의 내용을 입력해주세요 > ");
        return scanner.nextLine();
    }

    public static String getUserSelectNumber() {
        System.out.print("어떤 게시물을 조회할까요? ");
        return scanner.nextLine();
    }

    public static String getUserUpdateNumber() {
        System.out.print("어떤 게시물을 수정할까요? ");
        return scanner.nextLine();
    }

    public static String getUserDeleteNumber() {
        System.out.print("어떤 게시물을 삭제할까요? ");
        return scanner.nextLine();
    }
}
