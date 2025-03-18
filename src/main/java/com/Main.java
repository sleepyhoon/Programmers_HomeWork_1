package com;

import com.domain.config.AppConfig;
import com.domain.controller.PostController;

public class Main {
    public static void main(String[] args) {
        PostController postController = AppConfig.getBoardController();
        postController.play();
    }
}