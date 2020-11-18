package demo.daemon;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * DaemonsDontRunFinally
 *
 * @author liko
 * @date 2019/11/6
 */
class ADaemon implements Runnable {

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
            print("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            print("Exiting via InterruptedException");
        } finally {
            print("This should always run?");
        }
    }
}

public class DaemonsDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();

        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
