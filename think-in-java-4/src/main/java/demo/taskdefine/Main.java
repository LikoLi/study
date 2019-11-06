package demo.taskdefine;

import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @author liko
 * @date 2019/11/6
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new PrintTime(), "PrintTimeThread");
        thread.start();

        Thread thread2 = new PrintTime2("PrintTimeThread2");
        thread2.start();

    }
}
