package com.controller;

import com.view.InputView;
import java.util.Objects;

public class BoardController {
    private InputView inputView;

    public BoardController(InputView inputView) {
        this.inputView = inputView;
    }

    public void play() {
        while(true) {
            String userInput = inputView.getUserInput();
            if(Objects.equals(userInput, "종료") || Objects.equals(userInput, "exit")) {
                System.out.println("프로그램이 종료됩니다.");
                return;
            }
            System.out.println(userInput);
        }
    }

}
