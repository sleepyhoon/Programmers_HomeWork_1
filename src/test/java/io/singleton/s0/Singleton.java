package io.singleton.s0;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    // public 으로 하면 결국 Singleton 객체를 외부에서 만들게 됨.
    private Singleton() {
    }

    // static 이 없다면 Singleton 객체가 생성되어 있어야 함. (인스턴스 메서드이므로)
    public static Singleton getInstance() {
        return INSTANCE;
    }
}
