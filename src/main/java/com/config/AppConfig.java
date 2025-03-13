package com.config;

import com.controller.BoardController;
import com.view.InputView;

public abstract class AppConfig {
    private static final InputView inputview = new InputView();
    private static final BoardController boardController = new BoardController(inputview);

    public static BoardController getBoardController() {
        return boardController;
    }
}
