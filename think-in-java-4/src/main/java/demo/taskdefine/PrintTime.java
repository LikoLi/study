package demo.taskdefine;

import java.util.concurrent.TimeUnit;

/**
 * PrintTime
 *
 * @author liko
 * @date 2019/11/6
 */
public class PrintTime implements Runnable {
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
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.interrupted();
            }
        }
    }
}
