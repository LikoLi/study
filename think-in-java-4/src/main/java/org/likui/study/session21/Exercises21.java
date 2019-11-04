package org.likui.study.session21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exercises21
 *
 * @author liko
 * @date 2019/11/4
 */
public class Exercises21 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        R1 r1 = new R1();
        exec.execute(r1);
        exec.execute(new R2(r1));
        exec.shutdown();

    }
}

class R1 implements Runnable {

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
        try {
            synchronized (this) {
                System.out.println("R1 BEGIN!");
                wait();
                System.out.println("R1 End!");
            }
        } catch (InterruptedException e) {
            System.out.println("R1 Exit via interrupt");
        }
    }
}

class R2 implements Runnable {

    private Object lock;

    public R2(Object lock) {
        this.lock = lock;
    }

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
        System.out.println("R2 BEGIN!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            this.lock.notifyAll();
        }
        System.out.println("R2 END!");
    }
}
