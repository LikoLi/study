package org.likui.study.session21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FinxedThreadPool
 *
 * @author liko
 * @date 2019/10/27
 */
public class FinxedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();

    }
}
