package com;

import com.domain.Application;
import com.domain.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        Application application = AppConfig.getApplication();
        application.play();
    }
}