package io.singleton.s2;

public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
    }

    // Double-Checked Locking
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
