package org.likui.study.session21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercises14
 *
 * @author liko
 * @date 2019/11/4
 */
public class Exercises14 {
    private static final int SIZE = 10000;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new TimerRunner());
        }
        exec.shutdown();
    }
}

class TimerRunner implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Down!");
            }
        }, 2000);
    }
}
