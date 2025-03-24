package io.singleton.s2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class S2SingletonTest {
    @Test
    @DisplayName("volatile 와 synchronize 을 활용한 싱글톤 - 동시성 문제 해결")
    void test1() throws Exception {

        Thread thread1 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        });

        Thread thread2 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        });

        thread1.start();
        thread2.start();
    }
}
