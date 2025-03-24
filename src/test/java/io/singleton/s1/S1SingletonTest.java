package io.singleton.s1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class S1SingletonTest {
    @Test
    @DisplayName("메모리 낭비를 해결한 싱글톤 테스트 - Thread safe 하지 않음")
    void test1() throws Exception {
        Runnable task = () -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " - " + instance);
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        // 2개의 hashcode 값이 다르다. 이유는? 동시성 문제가 발생했기 때문이다.
        thread1.start();
        thread2.start();
    }
}
