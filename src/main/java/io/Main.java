package io;

import io.global.config.Container;

public class Main {
    public static void main(String[] args) {
        Application application = Container.getApplication();
        application.play();
    }
}