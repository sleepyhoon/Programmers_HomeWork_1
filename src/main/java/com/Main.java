package com;

import com.config.AppConfig;
import com.controller.BoardController;

public class Main {
    public static void main(String[] args) {
        BoardController boardController = AppConfig.getBoardController();
        boardController.play();
    }
}