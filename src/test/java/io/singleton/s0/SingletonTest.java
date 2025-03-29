package io.singleton.s0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingletonTest {
    @Test
    @DisplayName("가장 간단히 싱글톤 테스트 (2개가 같음) - 메모리가 낭비됨")
    void test1() throws Exception {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
    }

    @Test
    @DisplayName("thread safe 인지 테스트 (멀티스레드에도 같음)")
    void test2() throws Exception {
        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}