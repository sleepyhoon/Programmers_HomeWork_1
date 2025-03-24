package io.singleton.s1;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    // 메모리 낭비 문제가 해결됨.
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
