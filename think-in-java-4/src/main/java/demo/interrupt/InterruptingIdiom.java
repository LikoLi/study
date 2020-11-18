package demo.interrupt;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * InterruptingIdiom
 *
 * @author liko
 * @date 2019/11/4
 */

class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        print("NeedsCleanup " + id);
    }

    public void cleanup() {
        print("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

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
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    print("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        print("Calculating");
                        for (int i = 0; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        print("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            print("Exiting via while() test");
        } catch (InterruptedException e) {
            print("Exiting via InterruptedException.");
        }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            print("usage: java InterruptingIdiom delay-in-ms");
            System.exit(0);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }
}
