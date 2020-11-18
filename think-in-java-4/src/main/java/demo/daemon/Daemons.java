package demo.daemon;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Daemons
 *
 * @author liko
 * @date 2019/11/6
 */
class Daemon implements Runnable {

    private Thread[] t = new Thread[10];

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
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            print("DaemonSpawn " + i + " started. ");
        }

        for (int i = 0; i < t.length; i++) {
            print("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ". ");
        }

        while (true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {

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
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        print("d.isDaemon() = " + d.isDaemon() + ". ");

        TimeUnit.SECONDS.sleep(1);
    }
}
