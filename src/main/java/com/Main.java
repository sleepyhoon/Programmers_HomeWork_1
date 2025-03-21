package com;

import com.global.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        Application application = AppConfig.getApplication();
        application.play();
    }
}