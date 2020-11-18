package demo.join;

import static net.mindview.util.Print.print;

/**
 * Joining
 *
 * @author liko
 * @date 2019/11/6
 */
class Sleeper extends Thread {
    private int duration;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            // 捕获异常会清除interrupt标志
            print(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has awakened.");
    }
}

class Joinner extends Thread {
    private Sleeper sleeper;

    public Joinner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed.");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("Sleepy", 1500), grumpy = new Sleeper("Grumpy", 1500);
        Joinner dopey = new Joinner("Dopey", sleeper), doc = new Joinner("Doc", grumpy);
        grumpy.interrupt();
    }
}
