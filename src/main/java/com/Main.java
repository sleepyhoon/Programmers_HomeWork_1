package com;

import com.config.AppConfig;
import com.controller.PostController;

public class Main {
    public static void main(String[] args) {
        PostController postController = AppConfig.getBoardController();
        postController.play();
    }
}