package com.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        System.out.print("명령어 > ");
        return scanner.nextLine();
    }
}
