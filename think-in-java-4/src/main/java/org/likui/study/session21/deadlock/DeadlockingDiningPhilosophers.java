package org.likui.study.session21.deadlock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * DeadlockingDiningPhilosophers
 *
 * @author liko
 * @date 2019/11/5
 */
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws IOException, InterruptedException {
        int ponder = 0;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("deadlock-pool-%d").build();
        ExecutorService exec = Executors.newCachedThreadPool(factory);
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
//            if (i != size -1) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponder));
//            } else {
//                exec.execute(new PhilosopherLeft2Right(sticks[i], sticks[(i + 1) % size], i, ponder));
//            }
        }
        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
