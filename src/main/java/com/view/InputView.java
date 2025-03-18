package com.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String getUserCommand() {
        System.out.print("명령어 > ");
        return scanner.nextLine();
    }

    public String getUserPostTitle() {
        System.out.println("글의 제목을 입력해주세요 > ");
        return scanner.nextLine();
    }

    public String getUserPostContent() {
        System.out.println("글의 내용을 입력해주세요 > ");
        return scanner.nextLine();
    }

    public String getUserSelectNumber() {
        System.out.println("어떤 게시물을 조회할까요? ");
        return scanner.nextLine();
    }

    public String getUserUpdateNumber() {
        System.out.println("어떤 게시물을 수정할까요? ");
        return scanner.nextLine();
    }

    public String getUserDeleteNumber() {
        System.out.println("어떤 게시물을 삭제할까요? ");
        return scanner.nextLine();
    }
}
