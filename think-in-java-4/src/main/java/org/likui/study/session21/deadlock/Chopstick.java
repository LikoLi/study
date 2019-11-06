package org.likui.study.session21.deadlock;

/**
 * Chopstick
 *
 * @author liko
 * @date 2019/11/5
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
