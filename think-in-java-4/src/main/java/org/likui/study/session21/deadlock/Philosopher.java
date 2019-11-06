package org.likui.study.session21.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Philosopher
 *
 * @author liko
 * @date 2019/11/5
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);
    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
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
        try {
            while (!Thread.interrupted()) {
                print(this + " " + "thinking");
                pause();
                print(this + " " + "grabbing right");
                right.take();
                print(this + " " + "grabbing left");
                left.take();
                print(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
