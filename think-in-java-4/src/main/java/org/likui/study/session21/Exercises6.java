package org.likui.study.session21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exercises6
 *
 * @author liko
 * @date 2019/10/27
 */
public class Exercises6 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new Sleep());
        }
        exec.shutdown();
    }
}

class Sleep implements Runnable {

    @Override
    public void run() {
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(Thread.currentThread().getName() + " will sleep " + i + " second.");
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " exit.");
    }
}
